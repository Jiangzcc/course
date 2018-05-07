package com.jiangzhichao.controller.teacher;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.entity.TeacherDTO;
import com.jiangzhichao.service.teacher.TeacherInfoService;

/**
 * 教师信息Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/teacher")
public class TeacherInfoController extends BaseController {
	
	@Autowired
	private TeacherInfoService teacherInfoService;
	
	@RequestMapping("/confirmPassword")
	@ResponseBody
	public Map<String,Object> confirmPassword(HttpSession session,String password) {
		TeacherDTO teacher = (TeacherDTO) session.getAttribute("teacher");
		Map<String,Object> map = new HashMap<>(2);
		if(teacher.getTpassword().equals(password)) {
			map.put("result", true);
			return map;
		}
		map.put("result", false);
		return map;
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public Map<String,Object> changePassword(String oldPassword,String newPassword,HttpSession session) {
		TeacherDTO teacher = (TeacherDTO) session.getAttribute("teacher");
		Map<String,Object> map = new HashMap<>(2);
		//防止用户直接通过url访问、跳过输入旧密码验证，再次验证一遍
		if(teacher.getTpassword().equals(oldPassword)) {
			teacher.setTpassword(newPassword);
			int i = teacherInfoService.updatePassword(teacher);
			if(i!=0) {
				map.put("result", true);
				return map;
			}
		}
		map.put("result", false);
		return map;
	}
	
	@RequestMapping("/teacherInfo")
	@ResponseBody
	public TeacherDTO teacherInfo(HttpSession session) {
		return (TeacherDTO) session.getAttribute("teacher");
	}

}
