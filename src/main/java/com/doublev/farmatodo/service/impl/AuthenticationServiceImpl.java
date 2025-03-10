package com.doublev.farmatodo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.doublev.farmatodo.dao.request.SignUpRequest;
import com.doublev.farmatodo.dao.request.SigninRequest;
import com.doublev.farmatodo.dao.response.JwtAuthenticationResponse;
import com.doublev.farmatodo.model.Role;
import com.doublev.farmatodo.model.Usuario;
import com.doublev.farmatodo.repository.IUsuarioRepository;
import com.doublev.farmatodo.service.IAuthenticationService;
import com.doublev.farmatodo.service.IJwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	IUsuarioRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	IJwtService jwtService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		var user = Usuario.builder().nombres(request.getFirstName() + " " + request.getLastName())
				.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER)
				.build();
		userRepository.save(user);
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

	@Override
	public JwtAuthenticationResponse signin(SigninRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}
}
