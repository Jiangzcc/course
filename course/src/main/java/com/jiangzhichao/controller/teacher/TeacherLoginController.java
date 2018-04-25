package com.jiangzhichao.controller.teacher;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.enumm.LoginType;
import com.jiangzhichao.shiro.CustomizedToken;

/**
 * 教师登陆Controller--类似AdminLoginController，不过多注释
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/login")
@Scope("prototype")
public class TeacherLoginController extends BaseController{

	@RequestMapping("/teacherLogin")
	@ResponseBody
	public Map<String,Object> teacherLogin(String tno,String tpassword) {
		Map<String,Object> map = new HashMap<>();
		CustomizedToken  token = new CustomizedToken(tno, tpassword, LoginType.TEACHER.toString());
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
}