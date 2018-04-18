package com.jiangzhichao.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiangzhichao.service.admin.AdminLoginService;

@Controller
@RequestMapping("/login")
@Scope("prototype")
public class AdminLoginController {

	@Autowired
	private AdminLoginService adminLoginService;
	
	@RequestMapping("/adminLogin")
	public String adminLogin(String ausername,String apassword) {
		//AdminDO admin = adminLoginService.queryAdmin(ausername, apassword);
		UsernamePasswordToken token = new UsernamePasswordToken(ausername, apassword);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            /*message = messageService.message(MassageConstant.LOGIN_USER_REEOE);
            logger.error(message,uae);*/
        } catch (IncorrectCredentialsException ice) {
        } catch (LockedAccountException lae) {
        } catch (ExcessiveAttemptsException eae) {
        } catch (AuthenticationException ae) {
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
        }
        //验证是否登录成功
        if (!subject.isAuthenticated()) {
            token.clear();
        }
		return "redirect:index.html";
	}
	
}
