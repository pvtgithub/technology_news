package com.technology.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.technology.dto.UserDTO;
import com.technology.entity.UserEntity;
import com.technology.reponsitory.UserRepository;
import com.technology.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncode;

	@Override
	public UserDTO save(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userDTO.setPassword(passwordEncode.encode(userDTO.getPassword()));
		if (userDTO.getId() != null) {
			UserEntity oldUserEntity = userRepository.findOne(userDTO.getId());
			modelMapper.map(userDTO, oldUserEntity);
			userEntity = oldUserEntity;
		} else {
			userEntity = modelMapper.map(userDTO, UserEntity.class);
		}
		userEntity = userRepository.save(userEntity);
		return modelMapper.map(userEntity, UserDTO.class);
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			userRepository.delete(id);
		}
	}

	@Override
	public List<UserDTO> findAll(Pageable pageAble) {
		List<UserEntity> userListEntity = new ArrayList<>();
		List<UserDTO> userListDTO = new ArrayList<>();
		userListEntity = userRepository.findAll(pageAble).getContent();
		for (UserEntity item : userListEntity) {
			UserDTO dto = modelMapper.map(item, UserDTO.class);
			userListDTO.add(dto);
		}
		return userListDTO;
	}

	@Override
	public int countItemUser() {
		return (int) userRepository.count();
	}

}
