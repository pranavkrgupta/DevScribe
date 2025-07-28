package com.pranav.devscribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.devscribe.dto.BlogRequestDTO;
import com.pranav.devscribe.service.BlogService;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	// Create Blog
	@PostMapping
	public ResponseEntity<?> createBlog(@RequestParam Long userId, @RequestBody BlogRequestDTO blogRequestDTO){
		return new ResponseEntity<>(blogService.createBlog(blogRequestDTO, userId), HttpStatus.CREATED);
	}
}
