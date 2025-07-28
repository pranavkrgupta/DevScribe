package com.pranav.devscribe.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pranav.devscribe.cutomExceptions.ApiException;
import com.pranav.devscribe.cutomExceptions.ResourceNotFoundException;
import com.pranav.devscribe.dao.BlogDao;
import com.pranav.devscribe.dao.UserDao;
import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.BlogResponseDTO;
import com.pranav.devscribe.dto.UserDetailsResponseDTO;
import com.pranav.devscribe.dto.UserRegisterRequest;
import com.pranav.devscribe.entities.Blog;
import com.pranav.devscribe.entities.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	@Autowired
	private ModelMapper modelMapper;

	UserServiceImpl(BlogDao blogDao) {
		this.blogDao = blogDao;
	}

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
		User entity = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid User Id"));
		return modelMapper.map(entity, UserDetailsResponseDTO.class);
	}

	@Override
	public ApiResponse updateUserDetails(Long userId, UserRegisterRequest updatedUser) {
		if (userDao.existsByEmail(updatedUser.getEmail()))
			throw new ApiException("Duplicate Email - Update User failed");
		User entity = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User Id - Update User Failed"));
		modelMapper.map(updatedUser, entity);
		return new ApiResponse("Update User success.");
	}

	@Override
	public ApiResponse deleteUser(Long userId) {
		User entity = userDao.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User Id - Delete User Failed"));
		userDao.delete(entity);
		return new ApiResponse("User with Id " + userId + " Deleted Successfully");
	}

	@Override
	public List<BlogResponseDTO> getBlogsByUserId(Long userId) {
		List<Blog> blogs = blogDao.findByUserId(userId);
		return blogs.stream().map(blog -> {
			BlogResponseDTO dto = modelMapper.map(blog, BlogResponseDTO.class);
			return dto;
		}).toList();

	}

}
