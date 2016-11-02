package com.ttsofts.entity.app;

import com.ttsofts.entity.BaseEntity;

/**
 * 广告
 * @author chenjie
 */
public class AppAdvert extends BaseEntity {

	/**应用ID**/
	private String app_info_id;
	/**位置**/
	private String location;
	/**SDK ID**/
	private String sdkid;
	/**状态**/
	private String status;
	/**sdk**/
	private String sdk;


	public String getSdk() {
		return sdk;
	}

	public void setSdk(String sdk) {
		this.sdk = sdk;
	}

	public String getApp_info_id() {
		return app_info_id;
	}

	public void setApp_info_id(String app_info_id) {
		this.app_info_id = app_info_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSdkid() {
		return sdkid;
	}

	public void setSdkid(String sdkid) {
		this.sdkid = sdkid;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
