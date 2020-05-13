package com.example.springMVC.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.springMVC.cmd.IngredientCMD;
import com.example.springMVC.model.Ingredient;
import com.example.springMVC.model.Recipe;

import lombok.Synchronized;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Component
public class IngredientCMDToIngredient implements Converter<IngredientCMD, Ingredient> {

	final private UnitOfMesureCMDToUnitOfMeasure cmdToUom ;
	/**
	 * @param cmdToUom
	 */
	public IngredientCMDToIngredient(UnitOfMesureCMDToUnitOfMeasure cmdToUom) {
		super();
		this.cmdToUom = cmdToUom;
	}
	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCMD source) {
		if(source == null)
		return null;
		
		final Ingredient ingredient = new Ingredient();
		ingredient.setId(source.getId());
		ingredient.setAmnt(source.getAmnt());
		ingredient.setIndgrntsDscrptn(source.getIndgrntsDscrptn());
		ingredient.setUom(cmdToUom.convert(source.getUom()));
		if(source.getRecipeId() != null) {
			Recipe recipe = new Recipe();
			recipe.setId(source.getRecipeId());
			ingredient.setRecipe(recipe);
			recipe.addIngredient(ingredient);
		}
		return ingredient;
	}

}

