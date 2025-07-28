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
		return blogDao.findAll().stream().map(blog -> {
			BlogResponseDTO dto = modelMapper.map(blog, BlogResponseDTO.class);
			// dto.setUserName(blog.getUser().getFullname());
			// dto.setCategoryTitle(blog.getCategory().getTitle());
			// It is automatically done by modelMapper
			// default type is Eager for @ManyToOne and
			return dto;
		}).toList();
	}

	@Override
	public BlogResponseDTO getBlogById(Long blogId) {
		Blog entity = blogDao.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog Id not found"));
		BlogResponseDTO dto = modelMapper.map(entity, BlogResponseDTO.class);
		// dto.setUserFullName(entity.getUser().getFullname());
		// dto.setCategoryTitle(entity.getCategory().getTitle());
		// It is automatically done by modelMapper
		return dto;
	}

	@Override
	public ApiResponse updateBlogById(Long blogId, Long userId, BlogRequestDTO blogRequestDTO) {
		// User user = userDao.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Id not found"));
		Blog blog = blogDao.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog Id not found"));
		
		// Only verify the blog belongs to the user (optional)
	    if (blog.getUser().getId()!= userId) {
	        throw new ApiException("You cannot update this blog");
	    }
		Category category = categoryDao.getReferenceById(blogRequestDTO.getCategory_Id());
		blog.setTitle(blogRequestDTO.getTitle());
		blog.setContents(blogRequestDTO.getContents());
		blog.setCategory(category);
		return new ApiResponse("Update Succesfully");
	}

}
