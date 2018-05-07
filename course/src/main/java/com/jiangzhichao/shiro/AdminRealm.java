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
import com.jiangzhichao.dao.RoleMapper;
import com.jiangzhichao.dao.SubjectRoleMapper;
import com.jiangzhichao.entity.AdminDTO;
import com.jiangzhichao.entity.RoleDTO;
import com.jiangzhichao.entity.SubjectRoleDTO;
import com.jiangzhichao.util.SessionUtil;

/**
 * 自定义管理员Realm
 * 
 * @author BornToWin
 *
 */
public class AdminRealm extends AuthorizingRealm {
	
	@Autowired
	private AdminLoginMapper adminLoginMapper;
	
	@Autowired
	private SubjectRoleMapper subjectRoleMapper;

	@Autowired
	private RoleMapper roleMapper;
	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	//获取用户名
        String username = (String)super.getAvailablePrincipal(principalCollection);
        if(null != username){
        	//获取角色名--过时
        	//String role = loginType();
        	
        	//为当前用户设置角色和权限
        	List<SubjectRoleDTO> list = subjectRoleMapper.selectByNo(username);
        	List<String> roleList = new ArrayList<String>();
        	List<String> permissionList = new ArrayList<String>();
        	//循环赋值
        	for (SubjectRoleDTO subjectRoleDTO : list) {
				RoleDTO role = roleMapper.selectByPrimaryKey(subjectRoleDTO.getRoleno());
				//添加角色
				roleList.add(role.getRolename());
				//添加权限
				permissionList.add(role.getRolename());
			}
            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            //设置角色
            simpleAuthorInfo.addRoles(roleList);
            //设置权限
            simpleAuthorInfo.addStringPermissions(permissionList);
            return simpleAuthorInfo;
        } else {
            return null;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    	CustomizedToken token = (CustomizedToken) authenticationToken;
    	//获取管理员信息
        AdminDTO adminDTO = adminLoginMapper.selectByUsername(token.getUsername());
        //该管理员存在
        if (null != adminDTO) {
        	//认证
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(adminDTO.getAusername(),adminDTO.getApassword(),getName());
            //存入session
            SessionUtil.setSession("admin", adminDTO);
            return authcInfo;
        } else {
            return null;
        }
        //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常
    }
    
    /**
     * 过时、测试时用
     * @return
     */
    @SuppressWarnings("unused")
	private String loginType() {
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
        	//通过session了解当前用户角色
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