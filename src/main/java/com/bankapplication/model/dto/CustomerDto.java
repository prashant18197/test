package com.bankapplication.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.bankapplication.model.Account;
import com.bankapplication.model.Address;

public class CustomerDto {

	private String customerName;

	private Address address;

	private List<Account> accounts = new ArrayList<Account>();

	public CustomerDto() {
		super();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;

	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customer [ customerName=" + customerName + ", address=" + address + ", accounts=" + accounts + "]";
	}

}
