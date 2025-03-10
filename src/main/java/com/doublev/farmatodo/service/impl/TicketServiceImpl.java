package com.doublev.farmatodo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.doublev.farmatodo.model.StatusTicket;
import com.doublev.farmatodo.model.Ticket;
import com.doublev.farmatodo.model.Usuario;
import com.doublev.farmatodo.repository.ITicketRepository;
import com.doublev.farmatodo.repository.IUsuarioRepository;
import com.doublev.farmatodo.service.ITicketService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements ITicketService {

	private final ITicketRepository ticketRepository;
	private final IUsuarioRepository usuarioRepository;

	public Ticket crearTicket(Ticket ticket, UUID usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		ticket.setUsuario(usuario);
		return ticketRepository.save(ticket);
	}

	public Ticket actualizarTicket(UUID id, Ticket ticketDetalles) {
		return ticketRepository.findById(id).map(ticket -> {
			ticket.setDescripcion(ticketDetalles.getDescripcion());
			ticket.setStatus(ticketDetalles.getStatus());
			return ticketRepository.save(ticket);
		}).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
	}

	public void eliminarTicket(UUID id) {
		ticketRepository.deleteById(id);
	}

	public Ticket obtenerTicketPorId(UUID id) {
		return ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
	}

	public Page<Ticket> listarTicketsPaginados(int page, int size) {
		return ticketRepository.findAll(PageRequest.of(page, size));
	}

	public List<Ticket> filtrarTicketsPorEstatus(StatusTicket status) {
		return ticketRepository.findByStatus(status);
	}

	@Cacheable(value = "ticketsUsuario", key = "#usuarioId")
	public List<Ticket> filtrarTicketsPorUsuario(UUID usuarioId) {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return ticketRepository.findByUsuario(usuario);
	}

	public List<Ticket> filtrarTicketsPorUsuarioYEstado(UUID usuarioId, StatusTicket status) {
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		return ticketRepository.findByUsuarioAndStatus(usuario, status);
	}
}
