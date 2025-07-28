package com.pranav.devscribe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.devscribe.entities.Blog;

public interface BlogDao extends JpaRepository<Blog, Long>{

}
