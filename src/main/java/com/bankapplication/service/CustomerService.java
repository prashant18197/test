package com.bankapplication.service;

import com.bankapplication.entity.CustomerEntity;
import com.bankapplication.exceptionhandler.ResourceNotFoundException;
import com.bankapplication.model.Customer;

public interface CustomerService {

	public Customer saveCustomerDetails(Customer customer);

	public CustomerEntity getCustomerDetailsbyId(Integer id) throws ResourceNotFoundException;

	public void deleteCustomerDetailsbyId(Integer id);
}
