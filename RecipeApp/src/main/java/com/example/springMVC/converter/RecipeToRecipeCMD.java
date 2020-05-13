package com.example.springMVC.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.springMVC.cmd.RecipeCMD;
import com.example.springMVC.model.Ingredient;
import com.example.springMVC.model.Recipe;

import lombok.Synchronized;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Component
public class RecipeToRecipeCMD implements Converter<Recipe, RecipeCMD>{

	private IngredientToIngredientCMD ingredientToCMD ;
	private NotesToNotesCMD notesToCMD ;
	private CategoryToCategoryCMD categoryToCMD ;
	
	public RecipeToRecipeCMD(IngredientToIngredientCMD ingredientToCMD, NotesToNotesCMD notesToCMD,
			CategoryToCategoryCMD categoryToCMD) {
		this.ingredientToCMD = ingredientToCMD;
		this.notesToCMD = notesToCMD;
		this.categoryToCMD = categoryToCMD;
	}



	@Synchronized
	@Nullable
	@Override
	public RecipeCMD convert(Recipe source) {
		if(source == null)
		return null;
		
		final RecipeCMD recipeCMD =  new RecipeCMD();
		recipeCMD.setId(source.getId());
		recipeCMD.setCookTime(source.getCookTime());
		recipeCMD.setDifficulity(source.getDifficulity());
		recipeCMD.setDirections(source.getDirections());
		recipeCMD.setImage(source.getImage());
		recipeCMD.setPrepTime(source.getPrepTime());
		recipeCMD.setRespDscrptn(source.getRespDscrptn());
		recipeCMD.setSource(source.getSource());
		recipeCMD.setUrl(source.getUrl());
		recipeCMD.setServings(source.getServings());
		recipeCMD.setNotes(notesToCMD.convert(source.getNotes()));
		if(source.getCategories() != null && source.getCategories().size() > 0 ) {
			source.getCategories()
			.forEach(
					category -> recipeCMD.getCategories().add(categoryToCMD.convert(category))
			 );
		}
		if(source.getIngredients() != null && source.getIngredients().size() > 0) {
			for(Ingredient ingredient : source.getIngredients()) {
				recipeCMD.getIngredients().add(ingredientToCMD.convert(ingredient));
			}
		}
		return recipeCMD;
	}

}
