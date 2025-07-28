package com.pranav.devscribe.service;

import java.util.List;

import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.BlogRequestDTO;
import com.pranav.devscribe.dto.BlogResponseDTO;

public interface BlogService {

	ApiResponse createBlog(BlogRequestDTO blogRequestDTO, Long userId);

	List<BlogResponseDTO> getAllBlogs();

	BlogResponseDTO getBlogById(Long blogId);

	ApiResponse updateBlogById(Long blogId, Long userId, BlogRequestDTO blogRequestDTO);

}
