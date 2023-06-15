package com.amusement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amusement.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	

	public Optional<Customer> findByEmail(String email);
	
	@Query("SELECT c FROM Customer c WHERE c.isDeleted = false")
	public List<Customer> findAllNonDeletedCustomers();
}
