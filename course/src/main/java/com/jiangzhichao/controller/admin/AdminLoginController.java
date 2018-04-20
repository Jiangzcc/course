package com.jiangzhichao.controller.admin;

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

@Controller
@RequestMapping("/login")
@Scope("prototype")
public class AdminLoginController extends BaseController{

	@RequestMapping("/adminLogin")
	@ResponseBody
	public Map<String,Object> adminLogin(String ausername,String apassword) {
		Map<String,Object> map = new HashMap<>();
		CustomizedToken  token = new CustomizedToken(ausername, apassword, LoginType.ADMIN.toString());
		Subject subject = SecurityUtils.getSubject();
		
		try {
			subject.login(token);
		} /*catch (UnknownAccountException uae) {
		} catch (IncorrectCredentialsException ice) {
		} catch (LockedAccountException lae) {
		} catch (ExcessiveAttemptsException eae) {
		}*/ catch (AuthenticationException ae) {
			map.put("result", false);
			return map;
			//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
		}
		
		//验证是否登录成功
		if (!subject.isAuthenticated()) {
			token.clear();
			map.put("result", false);
			return map;
		}
		
		map.put("result", true);
		return map;
	}

}
