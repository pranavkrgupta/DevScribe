package com.pranav.devscribe.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="User")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true, exclude = "blogs")
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

	public User(String fullname, String email, String password, String phone) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.phone = phone;
	} 
}
