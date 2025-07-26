package com.pranav.devscribe.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Category")
@NoArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {
	
	@Column(name = "title", length = 100)
	private String title;
	
	@Column(name = "description", length = 100)
	private String description;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Blog> blogs = new ArrayList<>();
	
	public void addBlog(Blog blog) {
		blogs.add(blog);
		blog.setCategory(this);
	}
	
	public void removeBlog(Blog blog) {
		blogs.remove(blog);
		blog.setCategory(null);
	}
}

