package com.pranav.devscribe.service;

import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.UserRegisterRequest;

public interface UserService {
	
	public ApiResponse registerUser(UserRegisterRequest registerRequest);

}
