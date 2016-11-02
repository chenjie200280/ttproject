package com.ttsofts.entity.money;

import java.util.Date;

import com.ttsofts.entity.BaseEntity;

/**
 * 收入管理
 * @author chenjie
 */
public class MoneySalary extends BaseEntity {
	private String id;
	private String userid;
	private String planmoney;
	private String typeid;
	private String typename;
	private String money;
	private String remark;
	private Date addtime;
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getPlanmoney() {
		return planmoney;
	}
	public void setPlanmoney(String planmoney) {
		this.planmoney = planmoney;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
}
