package com.jiangzhichao.entity;

public class StudentVO {
	
	private String sno;
	private String sname;
	private String sterm;
	private String ssex;
	private String idcard;
	private String dname;
	private Integer score;
	
	public StudentVO() {
		super();
	}

	public StudentVO(String sno, String sname, String sterm, String ssex, String idcard, String dname, Integer score) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.sterm = sterm;
		this.ssex = ssex;
		this.idcard = idcard;
		this.dname = dname;
		this.score = score;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSterm() {
		return sterm;
	}

	public void setSterm(String sterm) {
		this.sterm = sterm;
	}

	public String getSsex() {
		return ssex;
	}

	public void setSsex(String ssex) {
		this.ssex = ssex;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "StudentVO [sno=" + sno + ", sname=" + sname + ", sterm=" + sterm + ", ssex=" + ssex + ", idcard="
				+ idcard + ", dname=" + dname + ", score=" + score + "]";
	}
	
}