package com.example.springMVC.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.springMVC.cmd.RecipeCMD;
import com.example.springMVC.model.Recipe;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
public interface RecipeService {
	public List<RecipeCMD> getAllRecipe();
	
    Recipe findById(Long l) ;
	public RecipeCMD findCommandById(Long Id);
	public RecipeCMD saveRecipeCommand(RecipeCMD recipeCmd);
    void deleteById(Long idToDelete);
    Page<Recipe> getAllRecipes(Pageable pageable);
	/**
	 * @return
	 */

    
}
