package com.amusement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.amusement.DTO.CustomerDTO;
import com.amusement.exception.CustomerException;
import com.amusement.model.Customer;
import com.amusement.repository.CustomerRepository;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void deleteCustomersIfDeletionTimePassed() {
		// TODO Auto-generated method stub
		customerRepository.deleteIfDeletionTimePassed();
	}

	@Override
	public List<CustomerDTO> getCustomersPageWise(Integer pageNumber, Integer recordsPerPage) throws CustomerException {
		// TODO Auto-generated method stub
		
		List<Customer> opt = customerRepository.findAllNonDeletedCustomers(PageRequest.of(pageNumber-1, recordsPerPage)).getContent();
		
		if(opt.isEmpty()) throw new CustomerException("No Customer available");
		
		List<CustomerDTO> customers = new ArrayList<>();
		opt.forEach(i -> customers.add(CustomerDTO.convertToCustomerDTO(i)));
		
		return customers;
	}

}
