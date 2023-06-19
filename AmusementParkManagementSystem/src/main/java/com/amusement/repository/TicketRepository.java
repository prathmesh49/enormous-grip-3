package com.amusement.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.amusement.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer>, PagingAndSortingRepository<Ticket, Integer>{
	
	@Query("SELECT SUM(t.personCount) FROM Ticket t WHERE t.visitDate = :localDate AND t.activity.activityId = :activityId")
	public Integer getPersonCountOfActivityForDate(@Param("localDate") LocalDate localDate, @Param("activityId") Integer activityId);
	
	
	@Modifying
    @Transactional
	@Query("DELETE FROM Ticket t WHERE t.ticketId = ?1")
    void deleteTicket(Integer ticketId);
}
