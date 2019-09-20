package com.bankapplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bankapplication.entity.AccountEntity;
import com.bankapplication.entity.CustomerEntity;
import com.bankapplication.exceptionhandler.ResourceNotFoundException;
import com.bankapplication.exceptionhandler.SystemException;
import com.bankapplication.model.Customer;
import com.bankapplication.model.dto.CustomerDto;
import com.bankapplication.repositories.AccountRepository;
import com.bankapplication.repositories.CustomerRepository;
import com.codepoetics.protonpack.Indexed;
import com.codepoetics.protonpack.StreamUtils;

@Service
public class CustomerServiceImpl implements CustomerService {
	private final static Logger logger = LoggerFactory.getLogger("CustomerServiceImpl.class");

	@Autowired
	private CustomerRepository customerRepository;
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private AccountRepository accountRepository;

	/**
	 * Method getAllCustomerDetails() is used to retrieve all the customerdetails;
	 * 
	 * @return List of customerDto class (@link CustomerDto).
	 */
	public List<CustomerDto> getAllCustomerDetails() {
		List<CustomerEntity> customerEntity = new ArrayList<CustomerEntity>();
		customerRepository.findAll().forEach(customerEntity::add);
		List<CustomerDto> customerDto = new ArrayList<CustomerDto>();
		for (CustomerEntity customer : customerEntity) {
			CustomerDto addCustomerDto = null;
			addCustomerDto = modelMapper.map(customer, CustomerDto.class);
			customerDto.add(addCustomerDto);
		}
		return customerDto;
	}

	/**
	 * Method getCustomerDetailsbyId() is used for retrieving customerDetails based
	 * on customer-id.
	 * 
	 * @return object of class customerEntity.
	 * @throws ResourceNotFoundException
	 * @throws SystemException
	 * 
	 */
	public CustomerEntity getCustomerDetailsbyId(Integer id) throws ResourceNotFoundException, SystemException {
		try {
			/*
			 * Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
			 * CustomerEntity customer = customerEntity.get(); return customer;
			 */
			long t1, t2, t3, t4;
			logger.info("The no of Processors available are [{}]", Runtime.getRuntime().availableProcessors());
			List<CustomerEntity> customerEntity = customerRepository.findAll();
			t1 = System.currentTimeMillis();
			CustomerEntity sequentialCustomer = customerEntity.stream().filter(x -> x.getCustomerId().equals(id))
					.findFirst().get();

			t2 = System.currentTimeMillis();
			logger.info("Sequential Stream Time Taken?= " + (t2 - t1));
			t3 = System.currentTimeMillis();

			CustomerEntity customer = customerEntity.parallelStream().filter(x -> x.getCustomerId().equals(id))
					.findFirst().get();
			t4 = System.currentTimeMillis();
			logger.info("Parallel Stream Time Taken?= " + (t4 - t3));

			return customer;

		} catch (Exception e) {
			logger.error("customer with id [{}] is not present", id);

			if (e.getMessage().equalsIgnoreCase("No value present")) {
				throw new ResourceNotFoundException("Customer with the given id [{}] is not present");
			} else {
				throw new SystemException("Internal system error or Database Connectivity lost");
			}

		}

	}

	/**
	 * Method saveCustomerDetails() is used for saving the customerDetails in the
	 * database.
	 * 
	 * @return object of Customer class.
	 */
	public Customer saveCustomerDetails(Customer customer) {
		CustomerEntity customerEntity = null;
		customerEntity = modelMapper.map(customer, CustomerEntity.class);
		return modelMapper.map(customerRepository.save(customerEntity), Customer.class);

	}

	/**
	 * 
	 * Method deleteCustomerDetailsbyId() is used for deleting customer details
	 * based on the customer id.
	 * 
	 * @throws ResourceNotFoundException
	 * @throws SystemException
	 */
	public void deleteCustomerDetailsbyId(Integer id) throws ResourceNotFoundException, SystemException {
		try {
			customerRepository.deleteById(id);
		} catch (Exception e) {
			logger.error("customer with id [{}] is not present", id);
			if (e.getMessage().equalsIgnoreCase("No value present")) {
				throw new ResourceNotFoundException("Customer with the given id is not present"+id);
			} else {
				throw new SystemException("Internal system error or Database Connectivity lost");
			}
		}
	}

	/**
	 * Method updateCustomerDetails() is used for updating customer details based on
	 * the customer id.
	 * 
	 * @param customer object of Customer class.
	 * @param id       customer id.
	 * @return object of customer class.
	 */
	public Customer updateCustomerDetails(Customer customer, Integer id) {

		Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
		CustomerEntity customerEntity = optionalCustomer.get();
		customerEntity.setCustomerName(customer.getCustomerName());
		return modelMapper.map(customerRepository.save(customerEntity), Customer.class);

	}

	/**
	 * Method getIndexedCustomerList() is used for getting the customer list based
	 * on the indexed.
	 * 
	 * @return Indexed customer list.
	 * @throws ResourceNotFoundException
	 * @throws SystemException
	 */
	public List<Indexed<CustomerEntity>> getIndexedCustomerList() throws ResourceNotFoundException, SystemException {

		long t1, t2, t3, t4;
		logger.info("The no of Processors available are [{}]", Runtime.getRuntime().availableProcessors());
		List<CustomerEntity> customerEntity = customerRepository.findAll();
		t1 = System.currentTimeMillis();
		List<Indexed<CustomerEntity>> sequentialCustomer = StreamUtils.zipWithIndex(customerEntity.stream())
				.filter(x -> x.getIndex() % 1 == 0).collect(Collectors.toList());
		t2 = System.currentTimeMillis();
		logger.info("Sequential Stream Time Taken?= " + (t2 - t1));
		t3 = System.currentTimeMillis();
		List<Indexed<CustomerEntity>> parallelCustomer = StreamUtils.zipWithIndex(customerEntity.parallelStream())
				.filter(x -> x.getIndex() % 1 == 0).collect(Collectors.toList());
		t4 = System.currentTimeMillis();
		logger.info("Parallel Stream Time Taken?= " + (t4 - t3));

		return parallelCustomer;
	}

	/**
	 * getAccountsOfBanerBranch() is used for retrieving accounts details of baner
	 * branch.
	 * 
	 * @return
	 * 
	 * @return List of object of accounts with branch baner.
	 */
	/*
	 * public List<AccountEntity> getAccountsOfBanerBranch() { long t1, t2;
	 * logger.info("The no of Processors available are [{}]",
	 * Runtime.getRuntime().availableProcessors()); List<AccountEntity> accounts =
	 * accountRepository.findBybranch("baner"); logger.info("test multirepo:[{}]",
	 * accounts); List<CustomerEntity> customerEntity =
	 * customerRepository.findAll(); t1 = System.currentTimeMillis();
	 * 
	 * customerEntity.stream().map(account -> account.getAccounts()).flatMap(account
	 * -> account.stream()) .filter(account ->
	 * account.getBranch().equals("baner")).collect(Collectors.toList()); t2 =
	 * System.currentTimeMillis(); logger.info("Sequential Stream Time Taken?= " +
	 * (t2 - t1)); t1 = System.currentTimeMillis();
	 * 
	 * List<AccountEntity> accountsOfBanerBranch = customerEntity.parallelStream()
	 * .map(account -> account.getAccounts()).flatMap(account -> account.stream())
	 * .filter(account ->
	 * account.getBranch().equals("baner")).collect(Collectors.toList()); t2 =
	 * System.currentTimeMillis(); logger.info("Parallel Stream Time Taken?= " + (t2
	 * - t1)); return accountsOfBanerBranch;
	 * 
	 * }
	 */
	/**
	 * Method getBranch() is used to getThe accountDetails of particular branch
	 * 
	 * @param branch
	 * @return CompletableFuture of branch Accounts.
	 */
	@Async
	public CompletableFuture<Stream<AccountEntity>> getBranch(String branch) {
		CompletableFuture<Stream<AccountEntity>> branchAccounts = CompletableFuture
				.completedFuture(accountRepository.findByBranch(branch));
		return branchAccounts;

	}

	/**
	 * Method getAccountType() is used to retrieve the accountDetails based on the
	 * type of account.
	 * 
	 * @param accountType
	 * @return CompletableFuture of AccountEntity.
	 */
	@Async
	public CompletableFuture<Stream<AccountEntity>> getAccountType(String accountType) {
		CompletableFuture<Stream<AccountEntity>> accountByType = CompletableFuture
				.completedFuture(accountRepository.findByAccountType(accountType));
		return accountByType;
	}

	/**
	 * Method getValidAccountDetailsUsingFuture() is used to retrieve all the valid
	 * account Details based on the baseAmount and accountType.
	 * 
	 * @param branch
	 * @param accountType
	 * @return
	 * @throws Throwable
	 */
	public List<Integer> getValidAccountDetailsUsingFuture(String branch, String accountType, Integer amount)
			throws Throwable {
		CompletableFuture<Stream<AccountEntity>> branchAccounts = getBranch(branch);
		CompletableFuture<Stream<AccountEntity>> accountByType = getAccountType(accountType);
		List<Integer> validCustomers;
		try {
			validCustomers = branchAccounts.get().parallel().map(x -> x.getAccountId()).collect(Collectors.toList());
			validCustomers.retainAll(accountByType.get().parallel().filter(x -> x.getBaseAmount() > amount)
					.map(x -> x.getAccountId()).collect(Collectors.toList()));
		} catch (Throwable exception) {
			throw exception.getCause();
		}
		return validCustomers;

	}

	/**
	 * Method getValidAccountDetailsUsingParallelStreams() used to retrieve valid
	 * accountDetails using parallelStreams.
	 * 
	 * @param branch
	 * @param type
	 * @return
	 */
	public List<Integer> getValidAccountDetailsUsingParallelStreams(String branch, String type, Integer amount) {
		Stream<AccountEntity> branchAccounts = accountRepository.findByBranch(branch);
		Stream<AccountEntity> accountTypes = accountRepository.findByAccountType(type);
		List<Integer> accountId = branchAccounts.parallel().map(account -> account.getAccountId())
				.collect(Collectors.toList());
		accountId.retainAll(accountTypes.parallel().filter(x -> x.getBaseAmount() > amount).map(x -> x.getAccountId())
				.collect(Collectors.toList()));
		return accountId;
	}

	/**
	 * Method getValidAccountDetailsUsingSequentialStreams() is used to get the
	 * validAccountDetails using sequential streams.
	 * 
	 * @param branch
	 * @param type
	 * @return
	 */
	public List<Integer> getValidAccountDetailsUsingSequentialStreams(String branch, String type, Integer amount) {
		Stream<AccountEntity> branchAccounts = accountRepository.findByBranch(branch);
		Stream<AccountEntity> accountTypes = accountRepository.findByAccountType(type);
		List<Integer> accountId = branchAccounts.map(account -> account.getAccountId()).collect(Collectors.toList());
		accountId.retainAll(accountTypes.filter(x -> x.getBaseAmount() > amount).map(x -> x.getAccountId())
				.collect(Collectors.toList()));
		return accountId;
	}

	/**
	 * Method findCustomerByName() is used to find customer by firstName.
	 * 
	 * @param firstName
	 * @return
	 */
	public CompletableFuture<List<CustomerEntity>> findCustomerByName(String customerName) {
		CompletableFuture<List<CustomerEntity>> customers = customerRepository.findByCustomerName(customerName);
		return customers;
	}
}
