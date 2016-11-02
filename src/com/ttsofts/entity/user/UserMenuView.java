package com.ttsofts.entity.user;

/**
 * 用户功能菜单
 * @author chenjie
 */
public class UserMenuView {
	private String id;
	private String name;
	private String url;
	private String remark;
	private String powerid;
	private String parentid;
	private String powername;
	

	public String getPowername() {
		return powername;
	}

	public void setPowername(String powername) {
		this.powername = powername;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPowerid() {
		return powerid;
	}

	public void setPowerid(String powerid) {
		this.powerid = powerid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

}
