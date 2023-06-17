package com.amusement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amusement.DTO.TicketDTO;
import com.amusement.exception.ActivityException;
import com.amusement.exception.CustomerException;
import com.amusement.exception.TicketException;
import com.amusement.service.TicketService;

import jakarta.validation.Valid;

@RestController
public class TicketController {
	
	@Autowired
	TicketService ticketService;
	
	@PostMapping("/tickets/{activityId}/{customerId}")
	public ResponseEntity<TicketDTO> createTicketHandler(
			@PathVariable Integer activityId,
			@PathVariable Integer customerId,
			@Valid @RequestBody TicketDTO ticketDTO
			) throws ActivityException, CustomerException {
		
		TicketDTO ticket = ticketService.createTicket(ticketDTO, activityId, customerId);
		
		return new ResponseEntity<>(ticket, HttpStatus.CREATED);
	}
	
	@PutMapping("/tickets/{customerId}/{ticketId}")
	public ResponseEntity<TicketDTO> updateTicketHandler(
			@PathVariable Integer customerId,
			@PathVariable Integer ticketId,
			@Valid @RequestBody TicketDTO ticketDTO) throws TicketException, CustomerException{
		
		TicketDTO ticket = ticketService.updateTicket(customerId, ticketId, ticketDTO);
		
		return new ResponseEntity<>(ticket, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/tickets/{customerId}/{ticketId}")
	public ResponseEntity<TicketDTO> getTicketByTicketId(
			@PathVariable Integer customerId, @PathVariable Integer ticketId) 
					throws TicketException, CustomerException{
		
		return new ResponseEntity<>(ticketService.getTicketById(customerId, ticketId), HttpStatus.OK);
	}
	
	@DeleteMapping("/tickets/{customerId}/{ticketId}")
	public ResponseEntity<TicketDTO> deleteTicketHandler(
			@PathVariable Integer customerId, @PathVariable Integer ticketId) 
					throws TicketException, CustomerException{
		
		ticketService.deleteTicket(customerId, ticketId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/tickets/{customerId}")
	public ResponseEntity<List<TicketDTO>> getTicketBookingHistoryHandler(
			@PathVariable Integer customerId,
			@RequestParam("page-number") Integer pageNumber,
			@RequestParam("recordsPerPage") Integer itemsPerPage) 
					throws TicketException, CustomerException{
		
		return new ResponseEntity<>(ticketService.getTicketBookingHistory(customerId, pageNumber, itemsPerPage), HttpStatus.OK);
	}
	
	
}
