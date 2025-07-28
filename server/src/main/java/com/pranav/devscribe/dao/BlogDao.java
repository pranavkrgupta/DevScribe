package com.pranav.devscribe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.devscribe.entities.Blog;

public interface BlogDao extends JpaRepository<Blog, Long>{
	
    @EntityGraph(attributePaths = {"user", "category"})
    List<Blog> findAll();
}
