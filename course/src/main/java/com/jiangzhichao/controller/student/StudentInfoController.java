package com.jiangzhichao.controller.student;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.entity.StudentDTO;
import com.jiangzhichao.service.student.StudentInfoService;

/**
 * 学生信息相关Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/student")
public class StudentInfoController extends BaseController {
	
	@Autowired
	private StudentInfoService studentInfoService;
	
	@RequestMapping("/confirmPassword")
	@ResponseBody
	public Map<String,Object> confirmPassword(HttpSession session,String password) {
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		Map<String,Object> map = new HashMap<>(2);
		if(student.getSpassword().equals(password)) {
			map.put("result", true);
			return map;
		}
		map.put("result", false);
		return map;
	}
	
	@RequestMapping("/changePassword")
	@ResponseBody
	public Map<String,Object> changePassword(String oldPassword,String newPassword,HttpSession session) {
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		Map<String,Object> map = new HashMap<>(2);
		//防止用户直接通过url访问、跳过输入旧密码验证，再次验证一遍
		if(student.getSpassword().equals(oldPassword)) {
			student.setSpassword(newPassword);
			int i = studentInfoService.updatePassword(student);
			if(i!=0) {
				map.put("result", true);
				return map;
			}
		}
		map.put("result", false);
		return map;
	}
	
	@RequestMapping("/studentInfo")
	@ResponseBody
	public StudentDTO studentInfo(HttpSession session) {
		StudentDTO student = (StudentDTO) session.getAttribute("student");
		StudentDTO studentDTO = studentInfoService.transDno(student);
		return studentDTO;
	}
}
