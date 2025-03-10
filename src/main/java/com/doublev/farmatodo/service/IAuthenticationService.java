package com.doublev.farmatodo.service;

import com.doublev.farmatodo.dao.request.SignUpRequest;
import com.doublev.farmatodo.dao.request.SigninRequest;
import com.doublev.farmatodo.dao.response.JwtAuthenticationResponse;

public interface IAuthenticationService {

	JwtAuthenticationResponse signup(SignUpRequest request);

	JwtAuthenticationResponse signin(SigninRequest request);
}
