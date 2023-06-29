package com.technology.service;

import com.technology.api.input.LoginDTO;

public interface IAuthService {
	String login(LoginDTO loginDTO);
}
