package com.bankapplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEntity {

	private String branch;

	private String accountType;
	private Float baseAmount;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "accountId")
	private Integer accountId;

	// @JoinColumn(name = "customer_id")
	// @ManyToOne(fetch = FetchType.LAZY)
	// private CustomerEntity customer;

	public AccountEntity() {

	}

	/*
	 * public CustomerEntity getCustomer() { return customer; }
	 * 
	 * public void setCustomer(CustomerEntity customer) { this.customer = customer;
	 * }
	 */

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public AccountEntity(Integer accountId) {
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
		return "AccountEntity [branch=" + branch + ", accountType=" + accountType + ", baseAmount=" + baseAmount
				+ ", accountId=" + accountId + "]";
	}

}
