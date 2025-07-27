package com.pranav.devscribe.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pranav.devscribe.entities.Category;

public interface CategoryDao extends JpaRepository<Category, Long>{

	boolean existsByTitle(String title);

	Optional<Category> findByTitle(String title);

}
