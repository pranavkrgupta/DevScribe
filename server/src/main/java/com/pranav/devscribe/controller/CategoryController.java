package com.pranav.devscribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.devscribe.dto.CategoryRequestDTO;
import com.pranav.devscribe.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public ResponseEntity<?> createCategory(CategoryRequestDTO  categoryRequestDTO){
		return ResponseEntity.ok(categoryService.createCategory(categoryRequestDTO));
	}
}
