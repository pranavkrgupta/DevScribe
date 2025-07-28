package com.pranav.devscribe.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pranav.devscribe.cutomExceptions.ApiException;
import com.pranav.devscribe.cutomExceptions.ResourceNotFoundException;
import com.pranav.devscribe.dao.BlogDao;
import com.pranav.devscribe.dao.CategoryDao;
import com.pranav.devscribe.dao.UserDao;
import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.BlogRequestDTO;
import com.pranav.devscribe.dto.BlogResponseDTO;
import com.pranav.devscribe.entities.Blog;
import com.pranav.devscribe.entities.Category;
import com.pranav.devscribe.entities.User;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse createBlog(BlogRequestDTO blogRequestDTO, Long userId) {
		User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id not found"));
		Category category = categoryDao.findById(blogRequestDTO.getCategory_Id())
				.orElseThrow(() -> new ResourceNotFoundException("Categoy Not Found"));
		Blog entity = new Blog();
		entity.setTitle(blogRequestDTO.getTitle());
		entity.setContents(blogRequestDTO.getContents());
		entity.setCategory(category);
		entity.setUser(user);
		blogDao.save(entity);
		return new ApiResponse("Blog Created with Id - " + entity.getId());
	}

	@Override
	public List<BlogResponseDTO> getAllBlogs() {
		return blogDao.findAll()
				.stream()
				.map(blog -> {
					BlogResponseDTO dto = modelMapper.map(blog, BlogResponseDTO.class);
					dto.setUserName(blog.getUser().getFullname());
					dto.setCategoryTitle(blog.getCategory().getTitle());
					return dto;
				}).toList();
	}

	@Override
	public BlogResponseDTO getBlogById(Long blogId) {
		Blog entity = blogDao.findById(blogId).orElseThrow(()-> new ResourceNotFoundException("Blog Id not found"));
		BlogResponseDTO dto = modelMapper.map(entity, BlogResponseDTO.class);
		dto.setUserName(entity.getUser().getFullname());
		return dto;
	}

}
