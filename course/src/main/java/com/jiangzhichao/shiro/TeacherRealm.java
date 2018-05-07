package com.jiangzhichao.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangzhichao.dao.TeacherMapper;
import com.jiangzhichao.entity.TeacherDTO;
import com.jiangzhichao.util.SessionUtil;

/**
 * 自定义教师Realm
 * 
 * @author BornToWin
 *
 */
public class TeacherRealm extends AuthorizingRealm {

	@Autowired
	private TeacherMapper teacherMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		CustomizedToken token = (CustomizedToken) authenticationToken;
		TeacherDTO teacherDTO = teacherMapper.selectByPrimaryKey(token.getUsername());
		if (null != teacherDTO) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(teacherDTO.getTno(),teacherDTO.getTpassword(),getName());
			SessionUtil.setSession("teacher", teacherDTO);
			return authcInfo;
		} else {
			return null;
		}
	}

}