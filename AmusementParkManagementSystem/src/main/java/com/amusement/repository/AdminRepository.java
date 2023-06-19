package com.amusement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amusement.model.Admin;
import com.amusement.model.Customer;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	public Optional<Customer> findByEmail(String email);
}
