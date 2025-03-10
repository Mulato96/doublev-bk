package com.doublev.farmatodo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.doublev.farmatodo.model.Usuario;

public interface IUsuarioService {

	public Usuario crearUsuario(Usuario usuario);

	public Usuario actualizarUsuario(UUID id, Usuario usuarioDetalles);

	public List<Usuario> listarUsuarios();

	public Usuario obtenerUsuarioPorId(UUID id);

	UserDetailsService userDetailsService();
}
