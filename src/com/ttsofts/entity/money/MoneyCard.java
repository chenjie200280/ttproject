package com.ttsofts.entity.money;

import com.ttsofts.entity.BaseEntity;

public class MoneyCard extends BaseEntity {
	private String id;
	private String cardId;
	private String bank;
	private String accountName;
	private String loseDate;
	private String validateId;
	private String repaymentDate;
	private String money;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getLoseDate() {
		return loseDate;
	}

	public void setLoseDate(String loseDate) {
		this.loseDate = loseDate;
	}

	public String getValidateId() {
		return validateId;
	}

	public void setValidateId(String validateId) {
		this.validateId = validateId;
	}

	public String getRepaymentDate() {
		return repaymentDate;
	}

	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

}