package com.jiangzhichao.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CustomizedToken extends UsernamePasswordToken {

	//��¼���ͣ��ж�ѧ����¼����ʦ��¼���ǹ���Ա��¼
	private String loginType;

	public CustomizedToken(final String username, final String password,String loginType) {
		super(username,password);
		this.loginType = loginType;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
}
