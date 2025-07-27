package com.pranav.devscribe.service;

import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.CategoryRequestDTO;

public interface CategoryService  {

	ApiResponse createCategory(CategoryRequestDTO categoryRequestDTO);

}
