package com.pranav.devscribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.devscribe.cutomExceptions.ApiException;
import com.pranav.devscribe.dto.UserRegisterRequest;
import com.pranav.devscribe.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/{userId}")
	@Operation(description = "Get User Details by Id")
	public ResponseEntity<?> getUserDetails(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.getUserDetails(userId));
	}

	@PutMapping("/{userId}")
	@Operation(description = "Update User Details By Id")
	public ResponseEntity<?> updateUserDetails(@PathVariable Long userId,
			@RequestBody UserRegisterRequest updatedUser) {
		return ResponseEntity.ok(userService.updateUserDetails(userId, updatedUser));
	}

	@DeleteMapping("/{userId}")
	@Operation(description = "Delete User by Id")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		return ResponseEntity.ok(userService.deleteUser(userId));
	}

	@GetMapping("/{userId}/blogs")
	@Operation(description = "Get all blogs by a user")
	public ResponseEntity<?> getBlogsByUser(@PathVariable Long userId) {
		return new ResponseEntity<>(userService.getBlogsByUserId(userId), HttpStatus.OK);
	}
}
