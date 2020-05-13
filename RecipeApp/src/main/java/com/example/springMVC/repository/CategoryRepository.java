package com.example.springMVC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springMVC.model.Category;

/**
 * Created By Prince for Project RecipeApp on Apr 13, 2020
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByCtgryDscrptn(String ctgryDscrptn);
	
}
