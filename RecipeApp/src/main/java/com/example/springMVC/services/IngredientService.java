package com.example.springMVC.services;

import com.example.springMVC.cmd.IngredientCMD;

/**
 * Created By Prince for Project RecipeApp on Apr 27, 2020
 *
 */
public interface IngredientService {
	IngredientCMD findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
    IngredientCMD saveIngredientCommand(IngredientCMD command);
    void deleteById(Long recipeId, Long idToDelete);
}
