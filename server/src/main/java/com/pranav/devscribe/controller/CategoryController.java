package com.pranav.devscribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.devscribe.dto.CategoryRequestDTO;
import com.pranav.devscribe.dto.UserRegisterRequest;
import com.pranav.devscribe.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	@Operation(description = "Add a Category")
	public ResponseEntity<?> createCategory(CategoryRequestDTO  categoryRequestDTO){
		return ResponseEntity.ok(categoryService.createCategory(categoryRequestDTO));
	}
	
	@GetMapping
	@Operation(description = "Get All Category")
	public ResponseEntity<?> getCategories(){
		return ResponseEntity.ok(categoryService.getCategories());
	}
	
	@GetMapping("/{categoryId}")
	@Operation(description = "Get Category Details by Id")
	public ResponseEntity<?> getCategoryDetails(@PathVariable Long categoryId){
		return ResponseEntity.ok(categoryService.getCategoryDetails(categoryId));
	}
	
	@PutMapping("/{categoryId}")
	@Operation(description = "Update Category Details By Id")
	public ResponseEntity<?> updateCategoryDetails(@PathVariable Long categoryId, @RequestBody CategoryRequestDTO updatedCategory){
		return ResponseEntity.ok(categoryService.updateCategoryDetails(categoryId, updatedCategory));
	}
	
	@DeleteMapping("/{categoryId}")
	@Operation(description = "Delete Category by Id")
	public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
		return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
	}
}
