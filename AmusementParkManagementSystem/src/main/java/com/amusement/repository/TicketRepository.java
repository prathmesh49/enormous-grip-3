package com.amusement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.amusement.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
