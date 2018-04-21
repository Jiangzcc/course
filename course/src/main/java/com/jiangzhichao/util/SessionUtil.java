package com.jiangzhichao.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Session��ع�����
 * 
 * @author BornToWin
 *
 */
public class SessionUtil {

	/**
     * ��һЩ���ݷŵ�ShiroSession��,�Ա��������ط�ʹ��
     * ����Controller,ʹ��ʱֱ����HttpSession.getAttribute(key)�Ϳ���ȡ��
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
