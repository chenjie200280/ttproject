package com.ttsofts.entity.money;

import com.ttsofts.entity.BaseEntity;

/**
 * 支出
 * @author chenjie
 */
public class MoneyPay extends BaseEntity {
	private String id;
	private String money;
	private String cardid;
	private String remark;
	private String addtime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}
