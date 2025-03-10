package com.doublev.farmatodo.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doublev.farmatodo.model.Usuario;
import com.doublev.farmatodo.repository.IUsuarioRepository;
import com.doublev.farmatodo.service.IUsuarioService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServiceImpl implements IUsuarioService {

	private final IUsuarioRepository usuarioRepository;

	public Usuario crearUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario actualizarUsuario(UUID id, Usuario usuarioDetalles) {
		return usuarioRepository.findById(id).map(usuario -> {
			usuario.setNombres(usuarioDetalles.getNombres());
			usuario.setApellidos(usuarioDetalles.getApellidos());
			return usuarioRepository.save(usuario);
		}).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}

	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	public Usuario obtenerUsuarioPorId(UUID id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) {
				return usuarioRepository.findByEmail(username)
						.orElseThrow(() -> new UsernameNotFoundException("User not found"));
			}
		};
	}
}
