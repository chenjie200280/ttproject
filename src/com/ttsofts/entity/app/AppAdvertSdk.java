package com.ttsofts.entity.app;

import com.ttsofts.entity.BaseEntity;

/**
 * SDK广告选择
 * 
 * @author chenjie
 *
 */
public class AppAdvertSdk extends BaseEntity {
	/**SDK 移动应用ID**/
	private String appid;
	/**SDK 广告ID**/
	private String adid;
	/**移动应用**/
	private String app_info_id;
	/**SDK提供商**/
	private String sdk;
	/**广告位置**/
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getApp_info_id() {
		return app_info_id;
	}

	public void setApp_info_id(String app_info_id) {
		this.app_info_id = app_info_id;
	}

	public String getSdk() {
		return sdk;
	}

	public void setSdk(String sdk) {
		this.sdk = sdk;
	}
}
