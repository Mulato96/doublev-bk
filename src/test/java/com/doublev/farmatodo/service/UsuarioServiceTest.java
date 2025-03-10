package com.doublev.farmatodo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.doublev.farmatodo.model.Role;
import com.doublev.farmatodo.model.Usuario;
import com.doublev.farmatodo.repository.IUsuarioRepository;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	@Mock
	private IUsuarioRepository usuarioRepository;

	@InjectMocks
	private IUsuarioService usuarioService;

	private Usuario usuario;

	@BeforeEach
	void setUp() {
		usuario = new Usuario(UUID.randomUUID(), "Juan", "Pérez", "a@b.com", "1234", Role.USER, null, null);
	}

	@Test
	void testObtenerUsuarioPorId() {
		when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

		Usuario resultado = usuarioService.obtenerUsuarioPorId(usuario.getId());

		assertNotNull(resultado);
		assertEquals("Juan", resultado.getNombres());
		assertEquals("Pérez", resultado.getApellidos());
	}
}
