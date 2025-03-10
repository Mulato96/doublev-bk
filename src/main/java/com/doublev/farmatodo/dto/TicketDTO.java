package com.doublev.farmatodo.dto;

import com.doublev.farmatodo.model.StatusTicket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDTO {

	@NotBlank(message = "La descripción no puede estar vacía")
	@Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
	private String descripcion;

	@NotNull(message = "El usuario es obligatorio")
	private String usuarioId;

	@NotNull(message = "El estado del ticket es obligatorio")
	private StatusTicket status;
}
