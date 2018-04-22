package com.jiangzhichao.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jiangzhichao.entity.CourseDO;
import com.jiangzhichao.entity.StudentDO;
import com.jiangzhichao.service.admin.AdminOpCourseInfoService;
import com.jiangzhichao.util.ExportExcel;
import com.jiangzhichao.util.ImportExcel;

/**
 * 管理员操作课程信息Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminOpCourserInfoController {

	private String cterm;
	@Value(value = "#{prop.term}")
	public void setCterm(String cterm) {  
		this.cterm = cterm;
	}

	@Autowired
	private AdminOpCourseInfoService adminOpCourseInfoService;

	@RequestMapping("queryAllCourse")
	@ResponseBody
	public Map<String,List<CourseDO>> queryAllCourse(){
		List<CourseDO> list = adminOpCourseInfoService.selectAllCourse();
		Map<String,List<CourseDO>> map = new HashMap<String,List<CourseDO>>();
		map.put("data", list);
		return map;
	}

	@RequestMapping("deleteCourse")
	@ResponseBody
	public Map<String,Object> deleteCourse(String cno){
		int i = adminOpCourseInfoService.deleteCourse(cno);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}

	@RequestMapping("queryCourse")
	@ResponseBody
	public CourseDO queryCourse(String cno){
		CourseDO teacherDO = adminOpCourseInfoService.selectCourseByCno(cno);
		return teacherDO;
	}

	@RequestMapping("editCourse")
	@ResponseBody
	public Map<String,Object> editCourse(CourseDO courseDO){
		if(courseDO.getDno().equals("")) {
			courseDO.setDno(null);
		}
		int i = adminOpCourseInfoService.updateCourse(courseDO);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}

	@RequestMapping("addCourse")
	@ResponseBody
	public Map<String,Object> addCourse(CourseDO courseDO) {
		courseDO.setCterm(cterm);
		courseDO.setCurrentnum(0);
		int i = adminOpCourseInfoService.insertCourse(courseDO);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
	
	/**
	 * 导入学生信息
	 * 
	 * @param file	只能是xls文件，否则会失败，暂时只考虑正常情况，不额外处理，上传后不删除。
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importCourse")
	@ResponseBody
	public Map<String,Object> importCourse(MultipartFile file,HttpServletRequest request) throws Exception{
		//获取文件名
		String filename = file.getOriginalFilename();
		//获取文件存储绝对路径
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		File f = new File(realPath,filename);
		//上传文件
		file.transferTo(f);
		int startRow=2;
		int endRow=0;
		//获取文件中详细信息
		@SuppressWarnings("unchecked")
		List<CourseDO> courseList = (List<CourseDO>) ImportExcel.importExcel(path, startRow, endRow, CourseDO.class);
		//导入教师信息到DB
		adminOpCourseInfoService.importCourse(courseList);
		Map<String,Object> map = new HashMap<>();
		map.put("result", true);
		return map;
	}

	/**
	 * 导出课程信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("exportCourse")
	public void exportCourse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<CourseDO> courses = adminOpCourseInfoService.selectAllCourse();
		//使用当前时间作为文件名
		String filename = System.currentTimeMillis() + ".xls";
		//获取路径
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		//导出文件
		String sheetName = "课程列表";
		String titleName = "课程信息";
		String[] headers = { "编号", "名称", "学分", "专业编号", "当前选课人数", "最大选课人数", "课程介绍", "授课教师编号", "所属学期" };
		String pattern = "yyyy-MM-dd";
		ExportExcel.exportExcel(sheetName, titleName, headers, courses, path, pattern);
		// 设置响应头和客户端保存文件名
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(path);
			os = response.getOutputStream();
			//循环写入输出流
			byte[] b = new byte[2048];
			int length;
			while ((length = is.read(b)) > 0) {
				os.write(b, 0, length);
			}
		} catch (Exception e){
			throw e;
		} finally {
			if(null != is)  is.close();
			if(null != os)  os.close();
		}
	}
}
