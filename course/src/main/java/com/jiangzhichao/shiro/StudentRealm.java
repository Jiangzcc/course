package com.jiangzhichao.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangzhichao.dao.StudentMapper;
import com.jiangzhichao.entity.StudentDTO;
import com.jiangzhichao.util.SessionUtil;

/**
 * 自定义学生Realm
 * 
 * @author BornToWin
 *
 */
public class StudentRealm extends AuthorizingRealm {

	@Autowired
	private StudentMapper studentMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		CustomizedToken token = (CustomizedToken) authenticationToken;
		StudentDTO studentDTO = studentMapper.selectByPrimaryKey(token.getUsername());
		if (null != studentDTO) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(studentDTO.getSno(),studentDTO.getSpassword(),getName());
			SessionUtil.setSession("student", studentDTO);
			return authcInfo;
		} else {
			return null;
		}
		//没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
	}

}