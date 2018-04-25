package com.jiangzhichao.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Session相关工具类
 * 
 * @author BornToWin
 *
 */
public class SessionUtil {

	/**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
	public static void setSession(String key, Object value){
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			Session session = currentUser.getSession();
			if(null != session){
				session.setAttribute(key, value);
			}
		}
	}
}
