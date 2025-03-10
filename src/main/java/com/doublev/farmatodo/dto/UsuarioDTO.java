package com.doublev.farmatodo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	@NotBlank(message = "El nombre es obligatorio")
	private String nombres;

	@NotBlank(message = "El apellido es obligatorio")
	private String apellidos;
}
