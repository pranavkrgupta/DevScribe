package com.pranav.devscribe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pranav.devscribe.entities.Blog;
import com.pranav.devscribe.entities.Category;

public interface BlogDao extends JpaRepository<Blog, Long>{
	
    @EntityGraph(attributePaths = {"user", "category"})
    List<Blog> findAll();

    // @EntityGraph(attributePaths = {"user", "category"})
	// List<Blog> findByTitleContainingIgnoreCase(String title);
    
    @Query("SELECT b FROM Blog b JOIN FETCH b.category JOIN FETCH b.user WHERE UPPER(b.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    List<Blog> searchBlogsByTitle(@Param("title") String title);

    @EntityGraph(attributePaths = {"user", "category"})
	List<Blog> findByCategoryId(Long categoryId);


}
