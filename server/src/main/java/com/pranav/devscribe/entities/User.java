package com.pranav.devscribe.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User")
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{
	@Column(name = "fullName", length = 20)
	private String fullname;
	
	@Column(name = "email", length = 50, unique = true)
	private String email;
	
	@Column(name = "password", length = 300, nullable = false)
	private String password;
	
	@Column(name = "phone_no.")
	private String phone;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Blog> blogs = new ArrayList<>(); 
}
