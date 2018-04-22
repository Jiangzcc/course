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
 * ����Ա�����γ���ϢController
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
	 * ����ѧ����Ϣ
	 * 
	 * @param file	ֻ����xls�ļ��������ʧ�ܣ���ʱֻ������������������⴦���ϴ���ɾ����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importCourse")
	@ResponseBody
	public Map<String,Object> importCourse(MultipartFile file,HttpServletRequest request) throws Exception{
		//��ȡ�ļ���
		String filename = file.getOriginalFilename();
		//��ȡ�ļ��洢����·��
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		File f = new File(realPath,filename);
		//�ϴ��ļ�
		file.transferTo(f);
		int startRow=2;
		int endRow=0;
		//��ȡ�ļ�����ϸ��Ϣ
		@SuppressWarnings("unchecked")
		List<CourseDO> courseList = (List<CourseDO>) ImportExcel.importExcel(path, startRow, endRow, CourseDO.class);
		//�����ʦ��Ϣ��DB
		adminOpCourseInfoService.importCourse(courseList);
		Map<String,Object> map = new HashMap<>();
		map.put("result", true);
		return map;
	}

	/**
	 * �����γ���Ϣ
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("exportCourse")
	public void exportCourse(HttpServletRequest request,HttpServletResponse response) throws IOException {
		List<CourseDO> courses = adminOpCourseInfoService.selectAllCourse();
		//ʹ�õ�ǰʱ����Ϊ�ļ���
		String filename = System.currentTimeMillis() + ".xls";
		//��ȡ·��
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		//�����ļ�
		String sheetName = "�γ��б�";
		String titleName = "�γ���Ϣ";
		String[] headers = { "���", "����", "ѧ��", "רҵ���", "��ǰѡ������", "���ѡ������", "�γ̽���", "�ڿν�ʦ���", "����ѧ��" };
		String pattern = "yyyy-MM-dd";
		ExportExcel.exportExcel(sheetName, titleName, headers, courses, path, pattern);
		// ������Ӧͷ�Ϳͻ��˱����ļ���
		response.setContentType("multipart/form-data");
		response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(path);
			os = response.getOutputStream();
			//ѭ��д�������
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
