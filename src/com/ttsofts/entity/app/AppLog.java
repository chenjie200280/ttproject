package com.ttsofts.entity.app;

import java.util.Date;

public class AppLog {
	private String id;
	
	private String mac;
	
	private String app_info_id;
	
	private String lonlat;
	
	private Date addtime;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getApp_info_id() {
		return app_info_id;
	}

	public void setApp_info_id(String app_info_id) {
		this.app_info_id = app_info_id;
	}

	public String getLonlat() {
		return lonlat;
	}

	public void setLonlat(String lonlat) {
		this.lonlat = lonlat;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}


}
