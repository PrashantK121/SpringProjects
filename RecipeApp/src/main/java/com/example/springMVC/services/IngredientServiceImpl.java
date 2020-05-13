package com.example.springMVC.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springMVC.cmd.IngredientCMD;
import com.example.springMVC.converter.IngredientCMDToIngredient;
import com.example.springMVC.converter.IngredientToIngredientCMD;
import com.example.springMVC.model.Ingredient;
import com.example.springMVC.model.Recipe;
import com.example.springMVC.repository.RecipeRepository;
import com.example.springMVC.repository.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Created By Prince for Project RecipeApp on Apr 27, 2020
 *
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private RecipeRepository recipeRepository ;
    private final IngredientToIngredientCMD ingredientToIngredientCommand;
    private final IngredientCMDToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IngredientServiceImpl(RecipeRepository recipeRepository,
			IngredientToIngredientCMD ingredientToIngredientCommand,
			IngredientCMDToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public IngredientCMD findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);	
		if(! recipeOptional.isPresent())
			log.error("recipe id not found. Id: " + recipeId);
		Recipe recipe = recipeOptional.get();
		Optional<IngredientCMD> ingredientOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
				.findFirst();
		if(! ingredientOptional.isPresent())
			log.error("Ingredient id not found: " + ingredientId);
		return ingredientOptional.get();
	}

	@Override
	public IngredientCMD saveIngredientCommand(IngredientCMD ingredientCMD) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCMD.getRecipeId());
		if(!recipeOptional.isPresent()) {
			log.error("Recipe not found for id: " + ingredientCMD.getRecipeId());
            return new IngredientCMD();
		}
		Recipe recipe = recipeOptional.get();
		Optional<Ingredient> ingredientOptional = recipe.getIngredients()
				.stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientCMD.getId()))
				.findFirst()
				;
		if(ingredientOptional.isPresent()) {
			Ingredient ingredientFound = ingredientOptional.get();
			ingredientFound.setIndgrntsDscrptn(ingredientCMD.getIndgrntsDscrptn());
            ingredientFound.setAmnt(ingredientCMD.getAmnt());
			ingredientFound.setUom(unitOfMeasureRepository
                    .findById(ingredientCMD.getUom().getId())
                    .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
		}else {
			Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCMD);
			ingredient.setRecipe(recipe);
			recipe.addIngredient(ingredient);
			
		}
		
		
		Recipe savedRecipe = recipeRepository.save(recipe);
		Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientCMD.getId()))
				.findFirst();
		if(!savedIngredientOptional.isPresent()) {
			savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(ingredient -> ingredient.getIndgrntsDscrptn().equals(ingredientCMD.getIndgrntsDscrptn()))
					.filter(ingredient -> ingredient.getAmnt().equals(ingredientCMD.getAmnt()))
					.filter(ingredient -> ingredient.getUom().getId().equals(ingredientCMD.getUom().getId()))
					.findFirst();
			
		}
		
		return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
	}

	@Override
	public void deleteById(Long recipeId, Long idToDelete) {
		
		Optional <Recipe> recipeOptioanl = recipeRepository.findById(recipeId);
		if(!recipeOptioanl.isPresent()) {
			Recipe recipe = recipeOptioanl.get();
			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
			.filter(ingredient -> ingredient.getId().equals(idToDelete)).findFirst();
			if(ingredientOptional.isPresent()) {
				Ingredient ingredientToDelete = ingredientOptional.get();
				ingredientToDelete.setRecipe(null);
				recipe.getIngredients().remove(ingredientToDelete);
				recipeRepository.save(recipe);
			}
		}else {
			 log.debug("Recipe Id Not found. Id:" + recipeId);
		}
	}

}
