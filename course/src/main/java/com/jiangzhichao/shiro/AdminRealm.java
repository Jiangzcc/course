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
import com.jiangzhichao.dao.RoleDOMapper;
import com.jiangzhichao.dao.SubjectRoleDOMapper;
import com.jiangzhichao.entity.AdminDO;
import com.jiangzhichao.entity.RoleDO;
import com.jiangzhichao.entity.SubjectRoleDOKey;
import com.jiangzhichao.util.SessionUtil;

public class AdminRealm extends AuthorizingRealm {
	
	@Autowired
	private AdminLoginMapper adminLoginMapper;
	
	@Autowired
	private SubjectRoleDOMapper subjectRoleDOMapper;

	@Autowired
	private RoleDOMapper roleDOMapper;
	
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
    	//��ȡ�û���
        String username = (String)super.getAvailablePrincipal(principalCollection);
        if(null != username){
        	//��ȡ��ɫ��--��ʱ
        	//String role = loginType();
        	
        	//Ϊ��ǰ�û����ý�ɫ��Ȩ��
        	List<SubjectRoleDOKey> list = subjectRoleDOMapper.selectByNo(username);
        	List<String> roleList = new ArrayList<String>();
        	List<String> permissionList = new ArrayList<String>();
        	//ѭ����ֵ
        	for (SubjectRoleDOKey subjectRoleDOKey : list) {
				RoleDO role = roleDOMapper.selectByPrimaryKey(subjectRoleDOKey.getRoleno());
				//��ӽ�ɫ
				roleList.add(role.getRolename());
				//���Ȩ��
				permissionList.add(role.getRolename()); //��ʱ
			}
            SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
            //���ý�ɫ
            simpleAuthorInfo.addRoles(roleList);
            //����Ȩ��
            simpleAuthorInfo.addStringPermissions(permissionList);
            return simpleAuthorInfo;
        } else {
            return null;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    	CustomizedToken token = (CustomizedToken) authenticationToken;
    	//��ȡ����Ա��Ϣ
        AdminDO adminDO = adminLoginMapper.selectByUsername(token.getUsername());
        //�ù���Ա����
        if (null != adminDO) {
        	//��֤
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(adminDO.getAusername(),adminDO.getApassword(),getName());
            //����session
            SessionUtil.setSession("admin", adminDO);
            return authcInfo;
        } else {
            return null;
        }
        //û�з��ص�¼�û�����Ӧ��SimpleAuthenticationInfo����ʱ,�ͻ���LoginController���׳�UnknownAccountException�쳣
    }
    
    //��ʱ������ʱ��
    @SuppressWarnings("unused")
	private String loginType() {
        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
        	//ͨ��session�˽⵱ǰ�û���ɫ
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