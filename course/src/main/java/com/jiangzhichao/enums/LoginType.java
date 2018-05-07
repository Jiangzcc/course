package com.jiangzhichao.enums;

/**
 * 登陆类型
 * 
 * @author BornToWin
 *
 */
public enum LoginType {
	/**
	 * 学生
	 */
	STUDENT("Student"),
	/**
	 * 管理员
	 */
	ADMIN("Admin"),
	/**
	 * 教师
	 */
	TEACHER("Teacher");

	private String type;

	private LoginType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type.toString();
	}
}
