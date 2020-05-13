package com.example.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.springMVC.cmd.RecipeCMD;
import com.example.springMVC.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created By Prince for Project RecipeApp on Apr 27, 2020
 *
 */
@Slf4j
@Controller
public class RecipeDetailsController {
	
	private final RecipeService recipeService; 
	
	public RecipeDetailsController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}


	@GetMapping("/recipe/{id}/show")
	public String showRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/show";
	}
	
	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCMD());
		return "recipe/recipeform" ;
	}
	
	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		 model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCMD recipeCMD) {
		RecipeCMD savedRecipeCMD = recipeService.saveRecipeCommand(recipeCMD);
		return "redirect:/recipe/"+savedRecipeCMD.getId()+"/show";
	}
	
	@GetMapping("recipe/{id}/delete")
	public String deleteRecipe(@PathVariable String id) {
		log.debug("Deleting id: " + id);
		recipeService.deleteById(Long.valueOf(id));
		return "redirect:/" ;
	}
	
	
}
