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

/**
 * ����Ա��½Controller
 * 
 * @author BornToWin
 *
 */
@Controller
@RequestMapping("/login")
@Scope("prototype")
public class AdminLoginController extends BaseController{

	/**
	 * ����Ա��½
	 * @param ausername �˺�
	 * @param apassword ����
	 * @return
	 */
	@RequestMapping("/adminLogin")
	@ResponseBody
	public Map<String,Object> adminLogin(String ausername,String apassword) {
		Map<String,Object> map = new HashMap<>();
		//����token���Զ����½����
		CustomizedToken  token = new CustomizedToken(ausername, apassword, LoginType.ADMIN.toString());
		//��ȡ��ǰ��ɫ
		Subject subject = SecurityUtils.getSubject();
		try {
			//��½��֤
			subject.login(token);
		} 
		// �򵥴���������ϸ��
		/*catch (UnknownAccountException uae) {
		} catch (IncorrectCredentialsException ice) {
		} catch (LockedAccountException lae) {
		} catch (ExcessiveAttemptsException eae) {
		}*/ 
		catch (AuthenticationException ae) {
			map.put("result", false);
			return map;
			//ͨ������Shiro������ʱAuthenticationException�Ϳ��Կ����û���¼ʧ�ܻ��������ʱ���龰
		}
		
		//��֤�Ƿ��¼�ɹ������жϽ���ȥ����ΪҪ��subject.isRemembered()���ʹ�á�
		//�����Ҿ��ǲ�ɾ
		if (!subject.isAuthenticated()) {
			token.clear();
			map.put("result", false);
			return map;
		}
		
		map.put("result", true);
		return map;
	}

}
