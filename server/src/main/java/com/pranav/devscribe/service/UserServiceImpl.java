package com.pranav.devscribe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pranav.devscribe.cutomExceptions.ApiException;
import com.pranav.devscribe.dao.UserDao;
import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.UserRegisterRequest;
import com.pranav.devscribe.entities.User;

import lombok.AllArgsConstructor;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public ApiResponse registerUser(UserRegisterRequest transientUser) {
		// Check if email exists
		if (userDao.existsByEmail(transientUser.getEmail()))
			throw new ApiException("Duplicate Email - Register User Failed");

		// save new user
		User userEntity = new User(transientUser.getFullname(), transientUser.getEmail(), transientUser.getPassword(), transientUser.getPhone());
		User persistentUser = userDao.save(userEntity);
		return new ApiResponse("Added new User with User Id: - " + persistentUser.getId());
	}

}
