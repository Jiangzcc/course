package com.jiangzhichao.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jiangzhichao.entity.StudentDO;
import com.jiangzhichao.service.admin.AdminOpStudentInfoService;
import com.jiangzhichao.util.ExportExcel;
import com.jiangzhichao.util.ImportExcel;

/**
 * 管理员操作学生信息Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminOpStudentInfoController {
	
	private String sterm;
	@Value(value = "#{prop.sterm}")
	public void setSterm(String sterm) {  
        this.sterm = sterm;
    }

	@Autowired
	private AdminOpStudentInfoService adminOpStudentInfoService;

	@RequestMapping("queryAllStudent")
	@ResponseBody
	public Map<String,List<StudentDO>> queryAllStudent(){
		List<StudentDO> list = adminOpStudentInfoService.selectAllStudent();
		Map<String,List<StudentDO>> map = new HashMap<String,List<StudentDO>>();
		map.put("data", list);
		return map;
	}

	@RequestMapping("deleteStudent")
	@ResponseBody
	public Map<String,Object> deleteStudent(String sno){
		int i = adminOpStudentInfoService.deleteStudent(sno);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}

	@RequestMapping("queryStudent")
	@ResponseBody
	public StudentDO queryStudent(String sno){
		StudentDO teacherDO = adminOpStudentInfoService.selectStudentBySno(sno);
		return teacherDO;
	}

	@RequestMapping("editStudent")
	@ResponseBody
	public Map<String,Object> editStudent(StudentDO studnetDo){
		int i = adminOpStudentInfoService.updateStudent(studnetDo);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}

	@RequestMapping("addStudent")
	@ResponseBody
	public Map<String,Object> addStudent(StudentDO studnetDo) {
		studnetDo.setSterm(sterm);
		int i = adminOpStudentInfoService.insertStudent(studnetDo);
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
	@RequestMapping("importStudent")
	@ResponseBody
	public Map<String,Object> importStudent(MultipartFile file,HttpServletRequest request) throws Exception{
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
		List<StudentDO> studentList = (List<StudentDO>) ImportExcel.importExcel(path, startRow, endRow, StudentDO.class);
		//导入教师信息到DB
		adminOpStudentInfoService.importStudent(studentList);
		Map<String,Object> map = new HashMap<>();
		map.put("result", true);
		return map;
	}

	/**
	 * 导出学生信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("exportStudent")
	public void exportStudent(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//查询所有教师信息
		List<StudentDO> list = adminOpStudentInfoService.selectAllStudent();
		//使用当前时间作为文件名
		String filename = System.currentTimeMillis() + ".xls";
		//获取路径
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		//导出文件
		String sheetName = "学生列表";
		String titleName = "学生信息";
		String[] headers = { "学号", "学生姓名", "学生密码", "学期", "专业编号", "性别", "身份证号" };
		String pattern = "yyyy-MM-dd";
		ExportExcel.exportExcel(sheetName, titleName, headers, list, path, pattern);
		// File file = new File(path);
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
