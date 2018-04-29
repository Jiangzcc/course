package com.jiangzhichao.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiangzhichao.controller.base.BaseController;
import com.jiangzhichao.enums.LoginType;
import com.jiangzhichao.shiro.CustomizedToken;

/**
 * 管理员登陆Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/login")
public class AdminLoginController extends BaseController {

	/**
	 * 管理员登陆
	 * @param ausername 账号
	 * @param apassword 密码
	 * @return
	 */
	@RequestMapping("/adminLogin")
	@ResponseBody
	public Map<String,Object> adminLogin(String ausername,String apassword) {
		Map<String,Object> map = new HashMap<>();
		//生成token、自定义登陆类型
		CustomizedToken  token = new CustomizedToken(ausername, apassword, LoginType.ADMIN.toString());
		//获取当前角色
		Subject subject = SecurityUtils.getSubject();
		try {
			//登陆验证
			subject.login(token);
		} 
		// 简单处理，不再做细化
		/*catch (UnknownAccountException uae) {
		} catch (IncorrectCredentialsException ice) {
		} catch (LockedAccountException lae) {
		} catch (ExcessiveAttemptsException eae) {
		}*/ 
		catch (AuthenticationException ae) {
			map.put("result", false);
			return map;
			//通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
		}
		
		//验证是否登录成功，此判断进不去，因为要和subject.isRemembered()结合使用。
		//但是我就是不删
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
	@RequestMapping("/adminLogout")
	public String adminLogout(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/login/admin/index.html";
	}

}
