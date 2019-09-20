package com.bankapplication.service;

import java.util.List;

import com.bankapplication.exceptionhandler.ResourceNotFoundException;
import com.bankapplication.model.Customer;

public interface CustomerServices {
	public List<Customer> getAllCustomerDetails();

	public Customer saveCustomerDetails(Customer customer);

	public Customer getCustomerDetailsbyId(Integer id) throws ResourceNotFoundException;

}
