package com.amusement.service;

import java.util.List;

import com.amusement.DTO.CustomerDTO;
import com.amusement.exception.CustomerException;

public interface AdminService {
	
	public void deleteCustomersIfDeletionTimePassed();
	
	public List<CustomerDTO> getCustomersPageWise(Integer pageNumber, Integer recordsPerPage) throws CustomerException;
}
