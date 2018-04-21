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
 * ����Ա����ѧ����ϢController
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
	 * ����ѧ����Ϣ
	 * 
	 * @param file	ֻ����xls�ļ��������ʧ�ܣ���ʱֻ������������������⴦���ϴ���ɾ����
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importStudent")
	@ResponseBody
	public Map<String,Object> importStudent(MultipartFile file,HttpServletRequest request) throws Exception{
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
		List<StudentDO> studentList = (List<StudentDO>) ImportExcel.importExcel(path, startRow, endRow, StudentDO.class);
		//�����ʦ��Ϣ��DB
		adminOpStudentInfoService.importStudent(studentList);
		Map<String,Object> map = new HashMap<>();
		map.put("result", true);
		return map;
	}

	/**
	 * ����ѧ����Ϣ
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("exportStudent")
	public void exportStudent(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//��ѯ���н�ʦ��Ϣ
		List<StudentDO> list = adminOpStudentInfoService.selectAllStudent();
		//ʹ�õ�ǰʱ����Ϊ�ļ���
		String filename = System.currentTimeMillis() + ".xls";
		//��ȡ·��
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		//�����ļ�
		String sheetName = "ѧ���б�";
		String titleName = "ѧ����Ϣ";
		String[] headers = { "ѧ��", "ѧ������", "ѧ������", "ѧ��", "רҵ���", "�Ա�", "���֤��" };
		String pattern = "yyyy-MM-dd";
		ExportExcel.exportExcel(sheetName, titleName, headers, list, path, pattern);
		// File file = new File(path);
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
