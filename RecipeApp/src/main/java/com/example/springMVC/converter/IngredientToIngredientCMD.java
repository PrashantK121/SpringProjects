package com.example.springMVC.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.springMVC.cmd.IngredientCMD;
import com.example.springMVC.model.Ingredient;

import lombok.Synchronized;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Component
public class IngredientToIngredientCMD implements Converter<Ingredient, IngredientCMD>{

	final private UnitOfMesureToUnitOfMeasureCMD uofToCMD ;
	public IngredientToIngredientCMD(UnitOfMesureToUnitOfMeasureCMD uofToCMD) {
		super();
		this.uofToCMD = uofToCMD;
	}
	@Synchronized
	@Nullable
	@Override
	public IngredientCMD convert(Ingredient source) {
		if(source == null)
		return null;
		
		final IngredientCMD ingredientCMD =  new IngredientCMD();
		ingredientCMD.setId(source.getId());
		ingredientCMD.setAmnt(source.getAmnt());
		ingredientCMD.setIndgrntsDscrptn(source.getIndgrntsDscrptn());
		ingredientCMD.setUom(uofToCMD.convert(source.getUom()));
		if(source.getRecipe()!=null) {
			ingredientCMD.setRecipeId(source.getRecipe().getId());
		}
		return ingredientCMD;
	}

}
