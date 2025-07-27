package com.pranav.devscribe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
	private String fullname;
	
	private String email;
	
	private String phone;
	
	private String password;
			
}
