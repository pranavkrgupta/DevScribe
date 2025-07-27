package com.pranav.devscribe.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pranav.devscribe.cutomExceptions.ApiException;
import com.pranav.devscribe.cutomExceptions.ResourceNotFoundException;
import com.pranav.devscribe.dao.CategoryDao;
import com.pranav.devscribe.dto.ApiResponse;
import com.pranav.devscribe.dto.CategoryRequestDTO;
import com.pranav.devscribe.dto.CategoryResponseDTO;
import com.pranav.devscribe.entities.Category;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse createCategory(CategoryRequestDTO categoryRequestDTO) {
		if (categoryDao.existsByTitle(categoryRequestDTO.getTitle()))
			throw new ApiException("Duplicate Category - Create Category Failed");
		Category entity = modelMapper.map(categoryRequestDTO, Category.class);
		categoryDao.save(entity);
		return new ApiResponse("Category created with Id - " + entity.getId());
	}

	@Override
	public List<CategoryResponseDTO> getCategories() {
		return categoryDao.findAll().stream().map(catgeory -> modelMapper.map(catgeory, CategoryResponseDTO.class))
				.toList();
	}

	@Override
	public CategoryResponseDTO getCategoryDetails(Long categoryId) {
		Category entity = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found by Id - " + categoryId));
		return modelMapper.map(entity, CategoryResponseDTO.class);
	}

	@Override
	public ApiResponse updateCategoryDetails(Long categoryId, CategoryRequestDTO updatedCategory) {
		Category entity = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found by Id - " + categoryId));
		Optional<Category> categoryWithSameTitle = categoryDao.findByTitle(updatedCategory.getTitle());
		if (categoryWithSameTitle.isPresent() && categoryWithSameTitle.get().getId() != categoryId)
			throw new ApiException("Duplicate Title - Update Failed");
		modelMapper.map(updatedCategory, entity);
		return new ApiResponse("Category with Id " + categoryId + " Updated Successfully");
	}

	@Override
	public ApiResponse deleteCategory(Long categoryId) {
		Category entity = categoryDao.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found by Id - " + categoryId));
		categoryDao.delete(entity);
		return new ApiResponse("Category with Id " + categoryId + " Deleted Successfully");
	}

}
