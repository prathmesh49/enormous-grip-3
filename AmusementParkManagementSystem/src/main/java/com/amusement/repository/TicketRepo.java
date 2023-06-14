package com.amusement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.amusement.model.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer>{
	
	@Query("select t from Ticket t where t.isDeleted=false")
	public List<Ticket> viewAllTickets();
}
