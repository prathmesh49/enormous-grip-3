package com.amusement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amusement.DTO.CustomerDTO;
import com.amusement.exception.CustomerException;
import com.amusement.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	

	@DeleteMapping("/admin/delete-process")
	public ResponseEntity<Void> deleteAllCustomersIfTimePassedHandler(){
		adminService.deleteCustomersIfDeletionTimePassed();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/admin/all-customers")
	public ResponseEntity<List<CustomerDTO>> getAllCustomersHandler(
			@RequestParam Integer pageNumber, @RequestParam Integer recordsPerPage) throws CustomerException{
		
		return new ResponseEntity<>(adminService.getCustomersPageWise(pageNumber, recordsPerPage), HttpStatus.OK);
	}
}
