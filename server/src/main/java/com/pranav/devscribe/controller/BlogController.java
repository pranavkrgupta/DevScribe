package com.pranav.devscribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.devscribe.dto.BlogRequestDTO;
import com.pranav.devscribe.service.BlogService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@PostMapping
	@Operation(description = "Create a new Blog")
	public ResponseEntity<?> createBlog(@RequestParam Long userId, @RequestBody BlogRequestDTO blogRequestDTO) {
		return new ResponseEntity<>(blogService.createBlog(blogRequestDTO, userId), HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(description = "get All blogs public + private")
	public ResponseEntity<?> getAllBlogs() {
		return new ResponseEntity<>(blogService.getAllBlogs(), HttpStatus.OK);
	}

	@GetMapping("/{blogId}")
	@Operation(description = "Get Blog by Id")
	public ResponseEntity<?> getBlogsById(@PathVariable Long blogId) {
		return new ResponseEntity<>(blogService.getBlogById(blogId), HttpStatus.OK);
	}

	@PutMapping("/{blogId}")
	@Operation(description = "Update Blog By Id")
	public ResponseEntity<?> updateBlogById(@PathVariable Long blogId, @RequestParam Long userId,
			@RequestBody BlogRequestDTO blogRequestDTO) {
		return new ResponseEntity<>(blogService.updateBlogById(blogId, userId, blogRequestDTO), HttpStatus.OK);
	}

	@DeleteMapping("/{blogId}")
	@Operation(description = "Delete Blog By Id")
	public ResponseEntity<?> deleteBlogById(@PathVariable Long blogId, @RequestParam Long userId) {
		return new ResponseEntity<>(blogService.deleteBlogById(blogId, userId), HttpStatus.OK);
	}

	@GetMapping("/search")
	@Operation(description = "Search Blogs By title")
	public ResponseEntity<?> searchBlogsByTitle(@RequestParam String title) {
		return new ResponseEntity<>(blogService.searchBlogsByTitle(title), HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getBlogsByCatgeory(@PathVariable Long categoryId){
		return new ResponseEntity<>(blogService.getBlogsByCatgeory(categoryId), HttpStatus.OK);
	}
}
