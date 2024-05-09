package com.ticketManagmentSystem.ticketTracker.dao;

import java.util.List;

import com.ticketManagmentSystem.ticketTracker.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query("SELECT t FROM Ticket t WHERE t.title LIKE CONCAT('%', :query, '%')"
			+ " Or t.description LIKE CONCAT('%', :query, '%')")
    List<Ticket> searchTickets(String query);
}
