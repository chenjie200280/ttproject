package com.ttsofts.entity.user;

import java.util.Date;

public class UserInfoView {
	//userinfo
	private String id;
	private String username;
	private String password;
	private String email;
	private Date begintime;
	private Date endtime;
	//power
	private String powerid;
	private String powername;

	public String getPowerid() {
		return powerid;
	}

	public void setPowerid(String powerid) {
		this.powerid = powerid;
	}

	public String getPowername() {
		return powername;
	}

	public void setPowername(String powername) {
		this.powername = powername;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
}
