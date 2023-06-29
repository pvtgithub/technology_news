package com.technology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.technology.api.input.LoginDTO;
import com.technology.service.impl.AuthService;

@RestController
public class Authentication {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginDTO loginDTO) {
		return authService.login(loginDTO);
	}
}
