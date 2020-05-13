package com.example.springMVC.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springMVC.model.Recipe;

/**
 * Created By Prince for Project RecipeApp on Apr 13, 2020
 *
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}
