package com.bankapplication.repositories;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankapplication.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
	
	Stream<AccountEntity> findByBranch(String firstBranch);
	Stream<AccountEntity> findByAccountType(String accountType);


}
/*
public List<AccountEntity> findBybranch(String branch);
	@Async
	CompletableFuture<List<AccountEntity>> findByBranch(String firstBranch);
	@Async
	List<AccountEntity> findByBranch(String firstBranch);
	@Async
	List<AccountEntity> findByAccountType(String accountType);
*/