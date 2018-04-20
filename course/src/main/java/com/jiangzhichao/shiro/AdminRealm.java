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
    	System.out.println("Admin ��Ȩ��======");
        String username = (String)super.getAvailablePrincipal(principalCollection);
        System.out.println(username+"=======");
        if(null != username){
        	String role = loginType();
        	System.out.println(role);
            //Ϊ��ǰ�û����ý�ɫ��Ȩ��
            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            List<String> roleList = new ArrayList<String>();
            roleList.add(role);		//��ʱ��������
            List<String> permissionList = new ArrayList<String>();
            permissionList.add(role);	//��ʱ��������
            simpleAuthorInfo.addRoles(roleList);
            simpleAuthorInfo.addStringPermissions(permissionList);
            return simpleAuthorInfo;
        } else {
            return null;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    	System.out.println("Admin ��֤��=========");
    	CustomizedToken token = (CustomizedToken) authenticationToken;
        AdminDO adminDO = adminLoginMapper.selectByUsername(token.getUsername());
        if (null != adminDO) {
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(adminDO.getAusername(),adminDO.getApassword(),getName());
            this.setSession("admin", adminDO);
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