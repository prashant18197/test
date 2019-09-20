package com.bankapplication.repositories;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.bankapplication.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

	// List<CustomerEntity> findByLastNameOrderByFirstNameAsc(String firstName);
	@Async
	CompletableFuture<List<CustomerEntity>> findByCustomerName(String customerName);
}
