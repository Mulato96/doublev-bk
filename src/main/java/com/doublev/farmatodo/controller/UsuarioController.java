package com.doublev.farmatodo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doublev.farmatodo.dto.UsuarioDTO;
import com.doublev.farmatodo.mappers.IUsuarioMapper;
import com.doublev.farmatodo.model.Usuario;
import com.doublev.farmatodo.service.IUsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Usuarios", description = "Operaciones CRUD para usuarios")
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

	private final IUsuarioService usuarioService;

	private final IUsuarioMapper usuarioMapper;

	@Operation(summary = "Crear un usuario", description = "Registra un nuevo usuario en la base de datos.")
	@PostMapping
	public ResponseEntity<UsuarioDTO> crearUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
		return ResponseEntity.ok(usuarioMapper.toDTO(usuarioService.crearUsuario(usuario)));
	}

	@Operation(summary = "Actualizar un usuario")
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable UUID id,
			@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
		return ResponseEntity.ok(usuarioMapper.toDTO(usuarioService.actualizarUsuario(id, usuario)));
	}

	@Operation(summary = "Obtener todos los usuarios", description = "Devuelve una lista de usuarios.")
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
		return ResponseEntity.ok(usuarioService.listarUsuarios().stream().map(usuarioMapper::toDTO).toList());
	}

	@Operation(summary = "Obtener un usuario por ID")
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable UUID id) {
		return ResponseEntity.ok(usuarioMapper.toDTO(usuarioService.obtenerUsuarioPorId(id)));
	}
}
