package com.amusement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.amusement.model.Ticket;
import com.amusement.service.TicketService;

@RestController
public class TestController {
	
	@Autowired
	TicketService ticketServ;
	
	@PostMapping("/test")
	public ResponseEntity<Ticket> TicketTest(@RequestBody Ticket ticket) {
		
		Ticket addedTicket = ticketServ.Test(ticket);
		return new ResponseEntity<>(addedTicket,HttpStatus.ACCEPTED);
	}
	
}
