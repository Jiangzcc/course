package com.jiangzhichao.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 自定义Token
 * 
 * @author BornToWin
 *
 */
public class CustomizedToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -4553808608648123587L;
	/**
	 * 登录类型，判断学生登录，教师登录还是管理员登录
	 */
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
