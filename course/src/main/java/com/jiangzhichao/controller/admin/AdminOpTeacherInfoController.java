package com.jiangzhichao.controller.admin;

import java.io.IOException;
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
import com.jiangzhichao.util.FileUtil;

/**
 * ����Ա������ʦ��ϢController
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminOpTeacherInfoController extends BaseController {

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

	/**
	 * �����ʦ��Ϣ
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importTeacher")
	@ResponseBody
	public Map<String,Object> importTeacher(MultipartFile file,HttpServletRequest request) throws Exception{
		//����Excel
		List<TeacherDO> teacherList = FileUtil.importExcel(file, 1, 1, TeacherDO.class);
		//�����ʦ��Ϣ��DB
		adminOpTeacherInfoService.importTeacher(teacherList);
		Map<String,Object> map = new HashMap<>();
		map.put("result", true);
		return map;

		/*
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
		List<TeacherDO> teacherList = (List<TeacherDO>) ImportExcel.importExcel(path, startRow, endRow, TeacherDO.class);
		*/
	}

	/**
	 * ������ʦ��Ϣ
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("exportTeacher")
	public void exportTeacher(HttpServletResponse response) throws IOException {
		List<TeacherDO> list = adminOpTeacherInfoService.selectAllTeacher();
		//��������
        FileUtil.exportExcel(list,"��ʦ��Ϣ","��ʦ��Ϣ",TeacherDO.class,"teachers.xls",response);
		
		/*//��ѯ���н�ʦ��Ϣ
		List<TeacherDO> list = adminOpTeacherInfoService.selectAllTeacher();
		//ʹ�õ�ǰʱ����Ϊ�ļ���
		String filename = System.currentTimeMillis() + ".xls";
		//��ȡ·��
		String realPath = request.getSession().getServletContext().getRealPath("/temporary");
		String path = realPath + "//" + filename;
		//�����ļ�
		String sheetName = "��ʦ�б�";
		String titleName = "��ʦ��Ϣ";
		String[] headers = { "��ʦ����", "��ʦ����", "��ʦ����", "��ʦְ��", "��ʦ�Ա�" };
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
		}*/
	}

}
