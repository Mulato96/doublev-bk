package com.doublev.farmatodo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.doublev.farmatodo.model.StatusTicket;
import com.doublev.farmatodo.model.Ticket;

public interface ITicketService {

	public Ticket crearTicket(Ticket ticket, UUID usuarioId);

	public Ticket actualizarTicket(UUID id, Ticket ticketDetalles);

	public void eliminarTicket(UUID id);

	public Ticket obtenerTicketPorId(UUID id);

	public Page<Ticket> listarTicketsPaginados(int page, int size);

	public List<Ticket> filtrarTicketsPorEstatus(StatusTicket status);

	public List<Ticket> filtrarTicketsPorUsuario(UUID usuarioId);

	public List<Ticket> filtrarTicketsPorUsuarioYEstado(UUID usuarioId, StatusTicket status);
}
