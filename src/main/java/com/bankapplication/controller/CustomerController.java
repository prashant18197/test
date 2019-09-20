package com.bankapplication.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.entity.CustomerEntity;
import com.bankapplication.exceptionhandler.ResourceNotFoundException;
import com.bankapplication.exceptionhandler.SystemException;
import com.bankapplication.model.Customer;
import com.bankapplication.model.dto.CustomerDto;
import com.bankapplication.repositories.CustomerRepository;
import com.bankapplication.service.CustomerServiceImpl;
import com.codepoetics.protonpack.Indexed;

/**
 * @author prashantnikam
 *
 */
@RestController
@RequestMapping("/customers")
@Transactional
public class CustomerController {
	private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerServiceImpl customerServiceImpl;
	@Autowired
	CustomerRepository customerRepository;
	private ModelMapper modelMapper = new ModelMapper();

	/**
	 * Function for retrieving all the customer Details.
	 * 
	 * @return List of all custoerDetails.
	 */
	@RequestMapping()
	public List<CustomerDto> getAllCustomerDetails() {
		logger.info("calling the getCustomerDetails of CustomerService class");
		return customerServiceImpl.getAllCustomerDetails();
	}

	/**
	 * Function for retrieving CustomerDetails based on the Customer id.
	 * 
	 * @param id CustomerId {@link Customer}.
	 * @return CustomerDto object based on the id.
	 * @throws ResourceNotFoundException
	 * @throws SystemException
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CustomerDto getCustomerDetailsbyId(@PathVariable Integer id)
			throws ResourceNotFoundException, SystemException {
		logger.info("calling the getCustomerDetailsbyId [{}] method of CustomerService class", id);
		return modelMapper.map(customerServiceImpl.getCustomerDetailsbyId(id), CustomerDto.class);

	}

	/**
	 * Function For deleting customer detail based on the id.
	 * 
	 * @param id CustomerId {@link Customer}.
	 * @throws ResourceNotFoundException
	 * @throws SystemException
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteCustomerDetailsbyId(@PathVariable Integer id) throws ResourceNotFoundException, SystemException {
		logger.info("calling the deleteCustomerDetailsbyId [{}] method of CustomerService class", id);
		customerServiceImpl.deleteCustomerDetailsbyId(id);

	}

	/**
	 * Function For saving the customer details.
	 * 
	 * @param customer Object of Customer class{@link Customer}.
	 * @return object of CustomerDto class{@link CustomerDto}.
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public CustomerDto saveCustomerDetails(@RequestBody Customer customer) {
		logger.info("calling the setCustomerDetails of CustomerService class");
		return modelMapper.map(customerServiceImpl.saveCustomerDetails(customer), CustomerDto.class);

	}

	/**
	 * Function for Updating the CustomerDetails.
	 * 
	 * @param id       CustomerId {@link Customer}.
	 * @param customer Object of class Customer.
	 * @return object of CustomerDto class{@link CustomerDto}.
	 */
	@PutMapping(value = "/{id}")
	public CustomerDto UpdateCustomerDetails(@RequestBody Customer customer,@PathVariable Integer id) {
		return modelMapper.map(customerServiceImpl.updateCustomerDetails(customer, id), CustomerDto.class);

	}

	/**
	 * Method getIndexedCustomerList() is used for getting the Indexed based
	 * customer list.
	 * 
	 * @return indexed based customerList.
	 */
	@GetMapping(value = "/indexedcustomer")
	public List<Indexed<CustomerEntity>> getIndexedCustomerList() {
		return customerServiceImpl.getIndexedCustomerList();
	}

	/**
	 * method getDeleteCount() is used to get the deletecount based on order by
	 * clause
	 * 
	 * @return count delete count
	 */
	/*
	 * @GetMapping(value = "/deletecount") public List<CustomerEntity>
	 * getDeleteCount() { List<CustomerEntity> count =
	 * customerRepository.findByLastNameOrderByFirstNameAsc("prashant"); return
	 * count; }
	 */

	/**
	 * Method getValidAccountDetailsUsingFuture() is used to get the valid customer
	 * details using the branch and AccountType.
	 * 
	 * @param branch
	 * @param accountType
	 * @return
	 * @throws Throwable
	 */
	@GetMapping(value = "/future", params = { "branch", "accountType", "baseAmount" })
	public List<Integer> getValidAccountDetailsUsingFuture(@RequestParam("branch") String branch,
			@RequestParam String accountType, @RequestParam Integer baseAmount) throws Throwable {

		return customerServiceImpl.getValidAccountDetailsUsingFuture(branch, accountType, baseAmount);
	}

	/**
	 * Method getValidAccountDetailsUsingParallelStreams() is used to get valid
	 * account details using parallelstreams.
	 * 
	 * @param branch
	 * @param accountType
	 * @return
	 * @throws Throwable
	 */
	@GetMapping(value = "/parallel", params = { "branch", "accountType", "baseAmount" })
	public List<Integer> getValidAccountDetailsUsingParallelStreams(@RequestParam("branch") String branch,
			@RequestParam String accountType, @RequestParam Integer baseAmount) throws Throwable {

		return customerServiceImpl.getValidAccountDetailsUsingParallelStreams(branch, accountType, baseAmount);
	}

	/**
	 * Method getValidAccountDetailsUsingSequentialStreams() is used to get valid
	 * accountDetails using sequentialstreams.
	 * 
	 * @param branch
	 * @param accountType
	 * @return
	 * @throws Throwable
	 */
	@GetMapping(value = "/streams", params = { "branch", "accountType", "baseAmount" })
	public List<Integer> getValidAccountDetailsUsingSequentialStreams(@RequestParam("branch") String branch,
			@RequestParam String accountType, @RequestParam Integer baseAmount) throws Throwable {

		return customerServiceImpl.getValidAccountDetailsUsingSequentialStreams(branch, accountType, baseAmount);
	}

	/**
	 * Method getCustomerByName() is used to get the customerDetails by firstName.
	 * accountDetails using sequentialstreams.
	 * 
	 * @param branch
	 * @param accountType
	 * @return
	 * @throws Throwable
	 */
	@GetMapping(value = "/firstName", params = { "firstName" })
	public CompletableFuture<List<CustomerEntity>> getCustomerByName(@RequestParam("firstName") String firstName)
			throws Throwable {

		return customerServiceImpl.findCustomerByName(firstName);
	}
}
