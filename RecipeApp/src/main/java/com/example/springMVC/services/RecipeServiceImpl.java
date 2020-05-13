package com.example.springMVC.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.springMVC.cmd.RecipeCMD;
import com.example.springMVC.converter.RecipeCMDToRecipe;
import com.example.springMVC.converter.RecipeToRecipeCMD;
import com.example.springMVC.exceptions.NotFoundException;
import com.example.springMVC.model.Recipe;
import com.example.springMVC.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepository recipeRepository ;
	private final RecipeToRecipeCMD recipeToRecipeCMD ;
	private final RecipeCMDToRecipe recipeCMDToRecipe ;
	
	@Override
	public Recipe findById(Long l) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		if (!recipeOptional.isPresent()) {
            throw new NotFoundException("Recipe Not Found. For ID value: " + l.toString() );
        }
		return recipeOptional.get();
	}

	@Override
	public void deleteById(Long idToDelete) {
		recipeRepository.deleteById(idToDelete);
	}

	public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCMD recipeToRecipeCMD,
			RecipeCMDToRecipe recipeCMDToRecipe) {
		this.recipeRepository = recipeRepository;
		this.recipeToRecipeCMD = recipeToRecipeCMD;
		this.recipeCMDToRecipe = recipeCMDToRecipe;
	}

	@Override
	public List<RecipeCMD> getAllRecipe() {
		List<Recipe> allRecipe = new ArrayList<Recipe>();
		List<RecipeCMD> allRecipeCMD = new ArrayList<RecipeCMD>(); 
		//recipeRepository.findAll().iterator().forEachRemaining(allRecipe :: add); //commented because the records are not adding in order
		allRecipe = recipeRepository.findAll();
		for(Recipe recipe_s : allRecipe) {
			allRecipeCMD.add(recipeToRecipeCMD.convert(recipe_s));
		}
		return allRecipeCMD;
	}

	@Override
	public RecipeCMD findCommandById(Long Id) {
		Optional<Recipe> recipeOptional =  recipeRepository.findById(Id);
		if(! recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe not found : "+Id);
		}
		return recipeToRecipeCMD.convert(recipeOptional.get());
	}

	@Override
	public RecipeCMD saveRecipeCommand(RecipeCMD recipeCmd) {
		Recipe detatchRecipe = recipeCMDToRecipe.convert(recipeCmd);
		Recipe savedRecipe = recipeRepository.save(detatchRecipe);
		if(log.isDebugEnabled()) {
			log.debug("Saved Recipe : "+ savedRecipe.getId());
		}
		RecipeCMD recipeCMD = recipeToRecipeCMD.convert(savedRecipe);
		return recipeCMD;
	}

	@Override
	public Page<Recipe> getAllRecipes(Pageable pageable) {
		 Page<Recipe> recipeList = recipeRepository.
				 findAll(pageable);
	        return recipeList;
	}

}
