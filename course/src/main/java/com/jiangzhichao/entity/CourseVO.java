package com.jiangzhichao.entity;

public class CourseVO {

	private String cno;
	private String cname;
	private Integer credit;
	private String dname;
	private Integer currentnum;
	private Integer maxnum;
	private String cintroduce;
	private String cterm;
	private String tname;
	
	public CourseVO() {
		super();
	}

	public CourseVO(String cno, String cname, Integer credit, String dname, Integer currentnum, Integer maxnum,
			String cintroduce, String cterm, String tname) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.credit = credit;
		this.dname = dname;
		this.currentnum = currentnum;
		this.maxnum = maxnum;
		this.cintroduce = cintroduce;
		this.cterm = cterm;
		this.tname = tname;
	}

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getCurrentnum() {
		return currentnum;
	}

	public void setCurrentnum(Integer currentnum) {
		this.currentnum = currentnum;
	}

	public Integer getMaxnum() {
		return maxnum;
	}

	public void setMaxnum(Integer maxnum) {
		this.maxnum = maxnum;
	}

	public String getCintroduce() {
		return cintroduce;
	}

	public void setCintroduce(String cintroduce) {
		this.cintroduce = cintroduce;
	}

	public String getCterm() {
		return cterm;
	}

	public void setCterm(String cterm) {
		this.cterm = cterm;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "CourseVO [cno=" + cno + ", cname=" + cname + ", credit=" + credit + ", dname=" + dname + ", currentnum="
				+ currentnum + ", maxnum=" + maxnum + ", cintroduce=" + cintroduce + ", cterm=" + cterm + ", tname="
				+ tname + "]";
	}

}
