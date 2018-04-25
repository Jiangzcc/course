package com.jiangzhichao.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangzhichao.dao.StudentDOMapper;
import com.jiangzhichao.entity.StudentDO;
import com.jiangzhichao.util.SessionUtil;

public class StudentRealm extends AuthorizingRealm {

	@Autowired
	private StudentDOMapper studentDOMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		CustomizedToken token = (CustomizedToken) authenticationToken;
		StudentDO studentDO = studentDOMapper.selectByPrimaryKey(token.getUsername());
		if (null != studentDO) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(studentDO.getSno(),studentDO.getSpassword(),getName());
			SessionUtil.setSession("student", studentDO);
			return authcInfo;
		} else {
			return null;
		}
		//没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
	}

}