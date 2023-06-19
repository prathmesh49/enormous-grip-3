package com.amusement.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amusement.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, PagingAndSortingRepository<Customer, Integer>{
	

	public Optional<Customer> findByEmail(String email);
	
	@Query("SELECT c FROM Customer c WHERE c.isDeleted = false")
	public Page<Customer> findAllNonDeletedCustomers(Pageable pageable);
	
	
	@Modifying
    @Transactional
	@Query("DELETE FROM Customer c WHERE c.deletionTime IS NOT NULL AND c.deletionTime < CURRENT_TIMESTAMP")
    void deleteIfDeletionTimePassed();
}
