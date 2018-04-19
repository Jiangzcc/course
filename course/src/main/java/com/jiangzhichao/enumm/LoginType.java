package com.jiangzhichao.enumm;

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
