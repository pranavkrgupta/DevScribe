package com.pranav.devscribe.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.devscribe.entities.User;

public interface UserDao extends JpaRepository<User, Long>{

	boolean existsByEmail(String email);

}
