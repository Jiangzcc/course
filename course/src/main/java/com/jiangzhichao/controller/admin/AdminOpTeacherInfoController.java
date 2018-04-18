package com.jiangzhichao.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.entity.TeacherDO;
import com.jiangzhichao.service.admin.AdminOpTeacherInfoService;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
public class AdminOpTeacherInfoController {
	
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

}
