package com.doublev.farmatodo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doublev.farmatodo.dto.TicketDTO;
import com.doublev.farmatodo.mappers.ITicketMapper;
import com.doublev.farmatodo.model.StatusTicket;
import com.doublev.farmatodo.model.Ticket;
import com.doublev.farmatodo.service.ITicketService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Tickets", description = "Operaciones CRUD para tickets")
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

	private final ITicketService ticketService;

	private final ITicketMapper ticketMapper;

	@Operation(summary = "Crear un ticket")
	@PostMapping("/{usuarioId}")
	public ResponseEntity<TicketDTO> crearTicket(@Valid @RequestBody TicketDTO ticketDTO,
			@PathVariable UUID usuarioId) {
		Ticket ticket = ticketMapper.toEntity(ticketDTO);
		return ResponseEntity.ok(ticketMapper.toDTO(ticketService.crearTicket(ticket, usuarioId)));
	}

	@Operation(summary = "Actualizar un ticket")
	@PutMapping("/{id}")
	public ResponseEntity<TicketDTO> actualizarTicket(@PathVariable UUID id, @Valid @RequestBody TicketDTO ticketDTO) {
		Ticket ticket = ticketMapper.toEntity(ticketDTO);
		return ResponseEntity.ok(ticketMapper.toDTO(ticketService.actualizarTicket(id, ticket)));
	}

	@Operation(summary = "Eliminar un ticket")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarTicket(@PathVariable UUID id) {
		ticketService.eliminarTicket(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Obtener un ticket por ID")
	@GetMapping("/{id}")
	public ResponseEntity<TicketDTO> obtenerTicketPorId(@PathVariable UUID id) {
		return ResponseEntity.ok(ticketMapper.toDTO(ticketService.obtenerTicketPorId(id)));
	}

	@Operation(summary = "Obtener todos los tickets paginados")
	@GetMapping
	public ResponseEntity<Page<Ticket>> listarTicketsPaginados(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		return ResponseEntity.ok(ticketService.listarTicketsPaginados(page, size));
	}

	@Operation(summary = "Obtener tickets filtrados")
	@GetMapping("/filtrar")
	public ResponseEntity<List<Ticket>> filtrarTickets(@RequestParam(required = false) UUID usuarioId,
			@RequestParam(required = false) StatusTicket status) {
		if (usuarioId != null && status != null) {
			return ResponseEntity.ok(ticketService.filtrarTicketsPorUsuarioYEstado(usuarioId, status));
		} else if (usuarioId != null) {
			return ResponseEntity.ok(ticketService.filtrarTicketsPorUsuario(usuarioId));
		} else if (status != null) {
			return ResponseEntity.ok(ticketService.filtrarTicketsPorEstatus(status));
		}
		return ResponseEntity.ok(ticketService.listarTicketsPaginados(0, 10).getContent());
	}
}
