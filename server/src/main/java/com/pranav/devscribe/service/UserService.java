package com.pranav.devscribe.service;

import java.util.List;

import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.BlogResponseDTO;
import com.pranav.devscribe.dto.UserDetailsResponseDTO;
import com.pranav.devscribe.dto.UserRegisterRequest;

public interface UserService {
	
	public ApiResponse registerUser(UserRegisterRequest registerRequest);

	public UserDetailsResponseDTO getUserDetails(Long userId);

	public ApiResponse updateUserDetails(Long userId, UserRegisterRequest updatedUser);

	public ApiResponse deleteUser(Long userId);

	public List<BlogResponseDTO> getBlogsByUserId(Long userId);

}
