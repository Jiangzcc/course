package com.jiangzhichao.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiangzhichao.dao.AdminLoginMapper;
import com.jiangzhichao.entity.AdminDO;

public class AdminRealm extends AuthorizingRealm {
	
	@Autowired
	private AdminLoginMapper adminLoginMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	System.out.println("Admin 授权器======");
        String username = (String)super.getAvailablePrincipal(principalCollection);
        System.out.println(username+"=======");
        if(null != username){
        	String role = loginType();
        	System.out.println(role);
            //为当前用户设置角色和权限
            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            List<String> roleList = new ArrayList<String>();
            roleList.add(role);		//暂时这样设置
            List<String> permissionList = new ArrayList<String>();
            permissionList.add(role);	//暂时这样设置
            simpleAuthorInfo.addRoles(roleList);
            simpleAuthorInfo.addStringPermissions(permissionList);
            return simpleAuthorInfo;
        } else {
            return null;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    	System.out.println("Admin 认证器=========");
    	CustomizedToken token = (CustomizedToken) authenticationToken;
        AdminDO adminDO = adminLoginMapper.selectByUsername(token.getUsername());
        if (null != adminDO) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(adminDO.getAusername(),adminDO.getApassword(),getName());
            this.setSession("admin", adminDO);
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
    
    private String loginType() {
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            if(null != session){
            	Object object = session.getAttribute("admin");
                if(null != object) {
                	return "admin";
                }
                object = session.getAttribute("student");
                if(null != object) {
                	return "student";
                }
                object = session.getAttribute("teacher");
                if(null != object) {
                	return "teacher";
                }
            }
        }
        return null;
    }
}