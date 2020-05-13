package com.example.springMVC.cmd;

import java.math.BigDecimal;

import com.example.springMVC.model.UnitOfMeasure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class IngredientCMD {
	private Long id ;
	private Long recipeId;
    private String indgrntsDscrptn ;
    private BigDecimal amnt ;
    private UnitOfMeasureCMD uom ;
}
