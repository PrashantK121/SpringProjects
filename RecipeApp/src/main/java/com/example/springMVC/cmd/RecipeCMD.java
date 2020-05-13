package com.example.springMVC.cmd;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.springMVC.model.Difficulity;

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
public class RecipeCMD {
	private Long id;
	private String respDscrptn;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions ;
	private List<IngredientCMD> ingredients = new ArrayList<IngredientCMD>();
	private Byte[] image ;
	private Difficulity difficulity ;
	private NotesCMD notes ;
	private Set<CategoryCMD> categories = new HashSet<CategoryCMD>();
}
