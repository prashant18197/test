package com.bankapplication.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class CustomerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerId")
	private Integer customerId;

	private String customerName;
	@JoinColumn(name = "addressId")
	@OneToOne(cascade = CascadeType.ALL)
	private AddressEntity address;

	@OneToMany(cascade = CascadeType.ALL/* , mappedBy = "customer" */)
	private List<AccountEntity> accounts;

	public CustomerEntity() {
		super();
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setAccounts(List<AccountEntity> accounts) {

		/*
		 * accounts.parallelStream().forEach(account -> { if (account.getCustomer() ==
		 * null) { account.setCustomer(this); } });
		 */
		this.accounts = accounts;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public List<AccountEntity> getAccounts() {
		return accounts;
	}

	@Override
	public String toString() {
		return "CustomerEntity [customerId=" + customerId + ", customerName=" + customerName + ", address=" + address
				+ ", accounts=" + accounts + "]";
	}

}
