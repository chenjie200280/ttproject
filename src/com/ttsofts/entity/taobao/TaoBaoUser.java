package com.ttsofts.entity.taobao;

import com.ttsofts.entity.BaseEntity;

public class TaoBaoUser extends BaseEntity {
	private String id;
	private String username;
	private String userpwd;
	private String paypwd;
	private String dueIn;
	private String week;
	private String month;
	private String grade;
	private String vnum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getPaypwd() {
		return paypwd;
	}

	public void setPaypwd(String paypwd) {
		this.paypwd = paypwd;
	}

	public String getDueIn() {
		return dueIn;
	}

	public void setDueIn(String dueIn) {
		this.dueIn = dueIn;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getVnum() {
		return vnum;
	}

	public void setVnum(String vnum) {
		this.vnum = vnum;
	}

}
