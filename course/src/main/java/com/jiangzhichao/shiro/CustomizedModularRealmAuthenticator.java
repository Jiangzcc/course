package com.jiangzhichao.shiro;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

/**
 * �Զ���Authenticator
 * ע�⣬����Ҫ�ֱ��崦����ͨ�û��͹���Ա��֤��Realmʱ����ӦRealm��ȫ����Ӧ�ð����ַ�����User�������ߡ�Admin����
 * ���ң����ǲ����໥���������磬������ͨ�û���֤��Realm��ȫ�����в�Ӧ�ð����ַ���"Admin"��
 */
public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // �ж�getRealms()�Ƿ񷵻�Ϊ��
        assertRealmsConfigured();
        // ǿ��ת�����Զ����CustomizedToken
        CustomizedToken customizedToken = (CustomizedToken) authenticationToken;
        // ��¼����
        String loginType = customizedToken.getLoginType();
        // ����Realm
        Collection<Realm> realms = getRealms();
        // ��¼���Ͷ�Ӧ������Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
        	/**
        	 * ע�⣬����Ҫ�ֱ��崦����ͨ�û��͹���Ա��֤��Realmʱ����ӦRealm��ȫ����Ӧ�ð����ַ�����User�������ߡ�Admin����
        	 * ���ң����ǲ����໥���������磬������ͨ�û���֤��Realm��ȫ�����в�Ӧ�ð����ַ���"Admin"��
        	 */
            if (realm.getName().contains(loginType))
                typeRealms.add(realm);
        }

        // �ж��ǵ�Realm���Ƕ�Realm
        if (typeRealms.size() == 1)
            return doSingleRealmAuthentication(typeRealms.iterator().next(), customizedToken);
        else
            return doMultiRealmAuthentication(typeRealms, customizedToken);
    }

}