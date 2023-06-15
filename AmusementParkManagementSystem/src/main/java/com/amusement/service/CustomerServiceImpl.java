package com.amusement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amusement.DTO.CustomerDTO;
import com.amusement.exception.ActivityException;
import com.amusement.exception.CustomerException;
import com.amusement.model.Activity;
import com.amusement.model.Customer;
import com.amusement.model.Role;
import com.amusement.model.Ticket;
import com.amusement.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public CustomerDTO registerCustomer(CustomerDTO customerDTO) throws CustomerException {
		// TODO Auto-generated method stub
		
		Optional<Customer> existingCustomer = customerRepository.findByEmail(customerDTO.getEmail());
		if(existingCustomer.isPresent()) throw new CustomerException("Email is already in use");
		
		Customer customer = customerDTO.convertToCustomer(customerDTO);
		customer.setRole(Role.USER);
		customer.setIsDeleted(false);
		
		Customer saved = customerRepository.save(customer);
		
		return customerDTO.convertToCustomerDTO(saved);
	}

	@Override
	public CustomerDTO updateCustomer(Integer customerId, CustomerDTO updatedCustomer) throws CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepository.findById(customerId);
		
		if(customer.isEmpty()) throw new CustomerException("Customer not found with customerId: "+customerId);
		if(customer.isPresent() && customer.get().getIsDeleted() == true) throw new CustomerException("Customer is deleted");
		
		customer.get().setAddress(updatedCustomer.getAddress());
		customer.get().setUsername(updatedCustomer.getUsername());
		
		return CustomerDTO.convertToCustomerDTO(customerRepository.save(customer.get()));
	}

	@Override
	public Boolean deleteCustomer(Integer customerId) throws CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> opt = customerRepository.findById(customerId);
		
		if(opt.isEmpty()) throw new CustomerException("Customer with customerId: " + customerId + " not found");
		if(opt.isPresent() && opt.get().getIsDeleted() == true) throw new CustomerException("Customer has already been deleted");
		
		opt.get().setIsDeleted(true);
		Customer customer = customerRepository.save(opt.get());
		
		if(customer != null) return true;
		else return false;
	}

	@Override
	public List<Activity> getActivitySuggestions(Integer customerId, Integer pageNumber, Integer itemsPerPage) throws CustomerException, ActivityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket bookActivityAndIssueTicket(Integer customerId, Integer activityId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
