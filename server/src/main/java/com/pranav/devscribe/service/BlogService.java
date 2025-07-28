package com.pranav.devscribe.service;

import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.BlogRequestDTO;

public interface BlogService {

	ApiResponse createBlog(BlogRequestDTO blogRequestDTO, Long userId);

}
