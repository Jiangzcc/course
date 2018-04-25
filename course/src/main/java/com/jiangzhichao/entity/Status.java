package com.jiangzhichao.entity;

/**
 * 选课/录入成绩--开启/关闭状态实体类
 * 
 * @author BornToWin
 *
 */
public class Status {

	private Boolean choice;
	private Boolean entry;
	
	public Status() {
		super();
	}
	
	public Status(Boolean choice, Boolean entry) {
		super();
		this.choice = choice;
		this.entry = entry;
	}
	
	public Boolean getChoice() {
		return choice;
	}
	
	public void setChoice(Boolean choice) {
		this.choice = choice;
	}
	
	public Boolean getEntry() {
		return entry;
	}
	
	public void setEntry(Boolean entry) {
		this.entry = entry;
	}
	
	@Override
	public String toString() {
		return "Status [choice=" + choice + ", entry=" + entry + "]";
	}
}
