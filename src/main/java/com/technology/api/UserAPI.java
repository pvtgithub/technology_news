package com.technology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.technology.api.output.UserOutput;
import com.technology.dto.UserDTO;
import com.technology.service.IUserService;

@RestController
public class UserAPI {

	@Autowired
	private IUserService userService;

	@PostMapping("/user")
	public UserDTO createUser(@RequestBody UserDTO userDTO) {
		return userService.save(userDTO);
	}

	@PutMapping("/user/{id}")
	public UserDTO updateUser(@RequestBody UserDTO userDTO, @PathVariable long id) {
		userDTO.setId(id);
		return userService.save(userDTO);
	}

	@DeleteMapping("/user")
	public void deleteUser(@RequestBody long[] ids) {
		userService.delete(ids);
	}

	@GetMapping("/user")
	public UserOutput getUser(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		UserOutput result = new UserOutput();
		result.setPage(page);
		Pageable pageAble = new PageRequest(page - 1, limit);
		result.setUserList(userService.findAll(pageAble));
		result.setTotalPage((int) Math.ceil((double) userService.countItemUser()/limit));
		return result;
	}
}
