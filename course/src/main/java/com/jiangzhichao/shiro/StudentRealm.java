package com.jiangzhichao.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangzhichao.dao.StudentDOMapper;
import com.jiangzhichao.entity.StudentDO;

public class StudentRealm extends AuthorizingRealm {

	@Autowired
	private StudentDOMapper studentDOMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		System.out.println("Student ��Ȩ��=========");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		System.out.println("Student ��֤��=========");
		CustomizedToken token = (CustomizedToken) authenticationToken;
		StudentDO studentDO = studentDOMapper.selectByPrimaryKey(token.getUsername());
		if (null != studentDO) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(studentDO.getSno(),studentDO.getSpassword(),getName());
			this.setSession("student", studentDO);
			return authcInfo;
		} else {
			return null;
		}
		//û�з��ص�¼�û�����Ӧ��SimpleAuthenticationInfo����ʱ,�ͻ���LoginController���׳�UnknownAccountException�쳣
	}

	/**
	 * ��һЩ���ݷŵ�ShiroSession��,�Ա��������ط�ʹ��
	 * ����Controller,ʹ��ʱֱ����HttpSession.getAttribute(key)�Ϳ���ȡ��
	 */
	private void setSession(String key, Object value){
		Subject currentUser = SecurityUtils.getSubject();
		if(null != currentUser){
			Session session = currentUser.getSession();
			if(null != session){
				session.setAttribute(key, value);
			}
		}
	}
}