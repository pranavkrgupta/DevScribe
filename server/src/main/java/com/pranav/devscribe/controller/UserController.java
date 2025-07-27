package com.pranav.devscribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.devscribe.cutomExceptions.ApiException;
import com.pranav.devscribe.dto.UserRegisterRequest;
import com.pranav.devscribe.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	@Operation(description = "Add New User")
	public ResponseEntity<?> registerUser(@RequestBody UserRegisterRequest user) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(user));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiException(e.getMessage()));
		}
	}
}
