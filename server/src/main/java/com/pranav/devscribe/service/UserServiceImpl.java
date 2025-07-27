package com.pranav.devscribe.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pranav.devscribe.cutomExceptions.ApiException;
import com.pranav.devscribe.cutomExceptions.ResourceNotFoundException;
import com.pranav.devscribe.dao.UserDao;
import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.UserDetailsResponseDTO;
import com.pranav.devscribe.dto.UserRegisterRequest;
import com.pranav.devscribe.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse registerUser(UserRegisterRequest transientUser) {
		// Check if email exists
		if (userDao.existsByEmail(transientUser.getEmail()))
			throw new ApiException("Duplicate Email - Register User Failed");

		// save new user
//		User userEntity = new User(transientUser.getFullname(), transientUser.getEmail(), transientUser.getPassword(), transientUser.getPhone());
		// using Model Mapper
		User userEntity = modelMapper.map(transientUser, User.class);
		User persistentUser = userDao.save(userEntity);
		return new ApiResponse("Added new User with User Id: - " + persistentUser.getId());
	}

	@Override
	public UserDetailsResponseDTO getUserDetails(Long userId) {
		User entity = userDao.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Invalid User Id"));
		return modelMapper.map(entity, UserDetailsResponseDTO.class);
	}

}
