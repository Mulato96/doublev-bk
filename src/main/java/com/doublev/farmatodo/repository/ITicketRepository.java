package com.doublev.farmatodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doublev.farmatodo.model.StatusTicket;
import com.doublev.farmatodo.model.Ticket;
import com.doublev.farmatodo.model.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket, UUID> {

	List<Ticket> findByUsuario(Usuario usuario);

	List<Ticket> findByStatus(StatusTicket status);

	@Query("SELECT t FROM Ticket t WHERE (:usuario IS NULL OR t.usuario = :usuario) AND (:status IS NULL OR t.status = :status)")
	List<Ticket> findByUsuarioAndStatus(@Param("usuario") Usuario usuario, @Param("status") StatusTicket status);

	Page<Ticket> findAll(Pageable pageable);
}
