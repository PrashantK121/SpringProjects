	package com.example.springMVC.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.springMVC.cmd.IngredientCMD;
import com.example.springMVC.cmd.RecipeCMD;
import com.example.springMVC.cmd.UnitOfMeasureCMD;
import com.example.springMVC.exceptions.NotFoundException;
import com.example.springMVC.services.IngredientService;
import com.example.springMVC.services.RecipeService;
import com.example.springMVC.services.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

/**
 * Created By Prince for Project RecipeApp on Apr 28, 2020
 *
 */
@Slf4j
@Controller
public class IngredientController {
	private final IngredientService ingredientService;
    private final RecipeService recipeService;
    private final UnitOfMeasureService unitOfMeasureService;
	
    public IngredientController(IngredientService ingredientService, RecipeService recipeService,
			UnitOfMeasureService unitOfMeasureService) {
		this.ingredientService = ingredientService;
		this.recipeService = recipeService;
		this.unitOfMeasureService = unitOfMeasureService;
	}
    
    @GetMapping("recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable String recipeId, Model model) {
    	model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
    	return "recipe/ingredient/list";
    }
    
    @GetMapping("recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showRecipeIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {
    	model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
    	return "recipe/ingredient/show";
    }
    
    @GetMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient (@PathVariable String recipeId, Model model) {
    	//make sure we have a good id value
    	RecipeCMD recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo raise exception if null
        //need to return back parent id for hidden form property
        IngredientCMD ingredientCommand = new IngredientCMD();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));
        ingredientCommand.setUom(new UnitOfMeasureCMD());
        model.addAttribute("ingredient", ingredientCommand);
        //init uom
        //ingredientCommand.setUom(new UnitOfMeasureCMD());
        model.addAttribute("uomList",  unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }
    
    @GetMapping("recipe/{recipeId}/ingredient/{id}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId, @PathVariable String id, Model model){
        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(id)));
        model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCMD command){
        IngredientCMD savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

       // return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredients";
    }

    @GetMapping("recipe/{recipeId}/ingredient/{id}/delete")
    public String deleteIngredient(@PathVariable String recipeId,
                                   @PathVariable String id){

        log.debug("deleting ingredient id:" + id);
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(id));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
   
}

