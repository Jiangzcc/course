package com.jiangzhichao.enums;

/**
 * 登陆类型
 * 
 * @author BornToWin
 *
 */
public enum LoginType {
	STUDENT("Student"),  ADMIN("Admin"), TEACHER("Teacher");

	private String type;

	private LoginType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type.toString();
	}
}
