package com.technology.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.technology.api.input.LoginDTO;
import com.technology.service.IAuthService;

@Service
public class AuthService implements IAuthService{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUntils jwtUntils;

	@Override
	public String login(LoginDTO loginDTO) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		System.out.println(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUntils.generateJwtToken(authentication);

        return token;
	}

}
