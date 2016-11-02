package com.ttsofts.entity.taobao;

import java.util.Date;

import com.ttsofts.entity.BaseEntity;

public class TaoBaoTask extends BaseEntity {
	/**编号**/
	private String id;
	/**qq**/
	private String qq;
	/**用户名**/
	private String username;
	/**qt**/
	private String qt;
	/**店名ID**/
	private String shopid;
	/**垫付**/
	private String pay;
	/**工资**/
	private String salary;
	/**返款账号**/
	private String account;
	/**是否支付**/
	private String ispay;
	/**备注**/
	private String remark;
	/**添加时间**/
	private Date addtime;
	/**查询开始时间**/
	private Date startDate;
	/**查询结束时间**/
	private Date endDate;
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getIspay() {
		return ispay;
	}

	public void setIspay(String ispay) {
		this.ispay = ispay;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getAddtime() {
		return addtime;	
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
}
