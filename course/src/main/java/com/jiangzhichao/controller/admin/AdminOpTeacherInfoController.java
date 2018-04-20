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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.entity.TeacherDO;
import com.jiangzhichao.service.admin.AdminOpTeacherInfoService;
import com.jiangzhichao.util.ExportExcel;
import com.jiangzhichao.util.ImportExcel;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminOpTeacherInfoController extends BaseController{

	@Autowired
	private AdminOpTeacherInfoService adminOpTeacherInfoService;

	@RequestMapping("queryAllTeacher")
	@ResponseBody
	public Map<String,List<TeacherDO>> queryAllTeacher(){
		List<TeacherDO> list = adminOpTeacherInfoService.selectAllTeacher();
		Map<String,List<TeacherDO>> map = new HashMap<String,List<TeacherDO>>();
		map.put("data", list);
		return map;
	}

	@RequestMapping("deleteTeacher")
	@ResponseBody
	public Map<String,Object> deleteTeacher(String tno){
		int i = adminOpTeacherInfoService.deleteTeacher(tno);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}

	@RequestMapping("queryTeacher")
	@ResponseBody
	public TeacherDO queryTeacher(String tno){
		TeacherDO teacherDO = adminOpTeacherInfoService.selectTeacherByTno(tno);
		return teacherDO;
	}

	@RequestMapping("editTeacher")
	@ResponseBody
	public Map<String,Object> editTeacher(TeacherDO teacherDO){
		int i = adminOpTeacherInfoService.updateTeacher(teacherDO);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}

	@RequestMapping("addTeacher")
	@ResponseBody
	public Map<String,Object> addTeacher(TeacherDO teacherDO) {
		int i = adminOpTeacherInfoService.insertTeacher(teacherDO);
		Map<String,Object> map = new HashMap<>();
		if(i==0) {
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}

	@RequestMapping("importTeacher")
	@ResponseBody
	public Map<String,Object> importTeacher(MultipartFile file,HttpServletRequest request) throws Exception{
		String filename = file.getOriginalFilename();
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		File f = new File(realPath,filename);
		file.transferTo(f);
		int startRow=2;
		int endRow=0;
		@SuppressWarnings("unchecked")
		List<TeacherDO> teacherList = (List<TeacherDO>) ImportExcel.importExcel(path, startRow, endRow, TeacherDO.class);
		adminOpTeacherInfoService.importTeacher(teacherList);
		Map<String,Object> map = new HashMap<>();
		map.put("result", true);
		return map;
	}

	@RequestMapping("exportTeacher")
	public void exportTeacher(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<TeacherDO> list = adminOpTeacherInfoService.selectAllTeacher();
		String filename = System.currentTimeMillis() + ".xls";
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		String sheetName = "教师列表";
		String titleName = "教师信息";
		String[] headers = { "教师工号", "教师姓名", "教师密码", "教师职称", "教师性别" };
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
