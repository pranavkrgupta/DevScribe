package com.pranav.devscribe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsResponseDTO {
	private Long id;
	private String fullname;
	private String email;
	private String phone;
}
