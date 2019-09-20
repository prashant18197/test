package com.bankapplication.model;

public class Account {
	private String branch;
	private String accountType;
	private Float baseAmount;
	private Integer accountId;

	public Account() {

	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Account(Integer accountId) {
		this.accountId = accountId;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public float getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(Float baseAmount) {
		this.baseAmount = baseAmount;
	}

	public Integer getAccountId() {
		return accountId;
	}

	@Override
	public String toString() {
		return "Account [branch=" + branch + ", accountType=" + accountType + ", baseAmount=" + baseAmount
				+ ", accountId=" + accountId + ", customer=" + /* customer + */"]";
	}

}
