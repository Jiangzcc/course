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
import com.jiangzhichao.util.SessionUtil;

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
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(teacherDO.getTno(),teacherDO.getTpassword(),getName());
			SessionUtil.setSession("teacher", teacherDO);
			return authcInfo;
		} else {
			return null;
		}
	}

}