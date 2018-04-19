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

import com.jiangzhichao.dao.TeacherDOMapper;
import com.jiangzhichao.entity.TeacherDO;

public class TeacherRealm extends AuthorizingRealm {

	@Autowired
	private TeacherDOMapper teacherDOMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		CustomizedToken token = (CustomizedToken) authenticationToken;
		TeacherDO teacherDO = teacherDOMapper.selectByPrimaryKey(token.getUsername());
		if (null != teacherDO) {
			// token.setPassword(adminDO.getApassword().toCharArray());
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(teacherDO.getTno(),teacherDO.getTpassword(),getName());
			this.setSession("teacher", teacherDO);
			return authcInfo;
		} else {
			return null;
		}
		//没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
	}

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
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