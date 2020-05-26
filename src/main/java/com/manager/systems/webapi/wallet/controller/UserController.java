package com.manager.systems.webapi.wallet.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.systems.webapi.wallet.dto.UserDTO;
import com.manager.systems.webapi.wallet.entity.User;
import com.manager.systems.webapi.wallet.response.Response;
import com.manager.systems.webapi.wallet.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<Response<UserDTO>> create(@Valid @RequestBody UserDTO dto, BindingResult result) {

		final Response<UserDTO> response = new Response<UserDTO>();

		final User user = this.userService.save(convertDTOToEntity(dto));
		response.setData(this.convertEntityToDTO(user));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	private User convertDTOToEntity(final UserDTO dto) {
		final User user = new User();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		return user;
	}

	private UserDTO convertEntityToDTO(final User user) {
		final UserDTO dto = new UserDTO();
		dto.setEmail(user.getEmail());
		dto.setName(user.getName());
		dto.setPassword(user.getPassword());
		return dto;
	}
}