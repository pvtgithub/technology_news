package com.technology.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.technology.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO userDTO);
	void delete(long[] ids);
	List<UserDTO> findAll(Pageable pageAble);
	int countItemUser();
}
