package com.jiangzhichao.controller.student;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.enums.LoginType;
import com.jiangzhichao.shiro.CustomizedToken;

/**
 * 学生登陆Controller--类似于AdminLoginController，不过多注释
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/login")
@Scope("prototype")
public class StudentLoginController extends BaseController{

	@RequestMapping("/studentLogin")
	@ResponseBody
	public Map<String,Object> studentLogin(String sno,String spassword) {
		Map<String,Object> map = new HashMap<>(2);
		CustomizedToken  token = new CustomizedToken(sno, spassword, LoginType.STUDENT.toString());
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (AuthenticationException ae) {
			map.put("result", false);
			return map;
		}
		if (!subject.isAuthenticated()) {
			token.clear();
			map.put("result", false);
			return map;
		}
		map.put("result", true);
		return map;
	}
	
	/**
	 * 登出
	 * @param session
	 * @return
	 */
	@RequestMapping("/studentLogout")
	public String adminLogout(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login/student/index.html";
	}
}
