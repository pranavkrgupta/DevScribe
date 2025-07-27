package com.pranav.devscribe.service;

import java.util.List;

import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.CategoryRequestDTO;
import com.pranav.devscribe.dto.CategoryResponseDTO;

public interface CategoryService  {

	ApiResponse createCategory(CategoryRequestDTO categoryRequestDTO);

	List<CategoryResponseDTO> getCategories();

	

}
