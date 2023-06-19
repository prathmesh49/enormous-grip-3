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
	
	@PostMapping("/tickets/{customerId}/{activityId}")
	public ResponseEntity<TicketDTO> createTicketHandler(
			@PathVariable Integer customerId,
			@PathVariable Integer activityId,
			@Valid @RequestBody TicketDTO ticketDTO
			) throws ActivityException, CustomerException {
		
		TicketDTO ticket = ticketService.createTicket(customerId, activityId, ticketDTO);
		
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
	public ResponseEntity<String> deleteTicketHandler(
			@PathVariable Integer customerId, @PathVariable Integer ticketId) 
					throws TicketException, CustomerException{
		
		Boolean deleted = ticketService.deleteTicket(customerId, ticketId);
		HttpStatus status;
		String message;
		
		System.out.println(deleted);
		if(deleted) {
			status = HttpStatus.OK;
			message = "Ticket deleted successfully";
		}else {
			status = HttpStatus.BAD_REQUEST;
			message = "Something went wrong";
		}
		
		return new ResponseEntity<>(message, status);
	}
	
	@GetMapping("/tickets/{customerId}")
	public ResponseEntity<List<TicketDTO>> getTicketBookingHistoryHandler(
			@PathVariable Integer customerId,
			@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("recordsPerPage") Integer itemsPerPage) 
					throws TicketException, CustomerException{
		
		return new ResponseEntity<>(ticketService.getTicketBookingHistory(customerId, pageNumber, itemsPerPage), HttpStatus.OK);
	}
	
	
}
