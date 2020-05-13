package com.example.springMVC.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.springMVC.cmd.RecipeCMD;
import com.example.springMVC.model.Recipe;

import lombok.Synchronized;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Component
public class RecipeCMDToRecipe implements Converter<RecipeCMD, Recipe>{

	private CategoryCMDToCategory CMDToCategoryConverter;
	private IngredientCMDToIngredient CMDToIngredientConverter;
	private NotesCMDToNotes CMDToNotesConverter;
	
	
	public RecipeCMDToRecipe(CategoryCMDToCategory cMDToCategoryConverter,
			IngredientCMDToIngredient cMDToIngredientConverter, NotesCMDToNotes cMDToNotesConverter) {
		super();
		CMDToCategoryConverter = cMDToCategoryConverter;
		CMDToIngredientConverter = cMDToIngredientConverter;
		CMDToNotesConverter = cMDToNotesConverter;
	}



	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCMD source) {
		if(source == null)
		return null;
		
		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setDifficulity(source.getDifficulity());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setRespDscrptn(source.getRespDscrptn());
		recipe.setServings(source.getServings());
		recipe.setDirections(source.getDirections());
		recipe.setUrl(source.getUrl());
		recipe.setImage(source.getImage());
		recipe.setSource(source.getSource());
		recipe.setNotes(CMDToNotesConverter.convert(source.getNotes()));
		if(source.getCategories() != null &&  source.getCategories().size() > 0) {
			source.getCategories()
			.forEach(
					category -> recipe.getCategories().add(CMDToCategoryConverter.convert(category))
			 );
		}
		if(source.getIngredients() != null && source.getIngredients().size()>0) {
			source.getIngredients()
			.forEach(
					ingredient -> recipe.getIngredients().add(CMDToIngredientConverter.convert(ingredient))
			 );
		}
		return recipe;
	}

}
