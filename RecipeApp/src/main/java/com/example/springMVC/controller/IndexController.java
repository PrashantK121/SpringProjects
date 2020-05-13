package com.example.springMVC.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springMVC.exceptions.NotFoundException;
import com.example.springMVC.model.PageWrapper;
import com.example.springMVC.model.Recipe;
import com.example.springMVC.repository.RecipeRepository;
import com.example.springMVC.services.RecipeService;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Controller
public class IndexController {
	private RecipeService recipeService;
	private RecipeRepository recipeRepository;
	public IndexController(RecipeService recipeService,RecipeRepository recipeRepository) {
		this.recipeService = recipeService;
		this.recipeRepository = recipeRepository;
	}
	
	@GetMapping("list")
	//@ResponseBody
	public String getRecipeListPage(Model model, @RequestParam(defaultValue="0") int page) {
		
		Page<Recipe> recipePage = recipeRepository.findAll(
				PageRequest.of(page, 1, 
						Sort.by("id").ascending()
						.and(Sort.by("respDscrptn"))
						)
				);
		model.addAttribute("recipes", recipePage );
		model.addAttribute("currentPage", page);
		return "pagination";
		//return recipeRepository.findAll(PageRequest.of(page, 5))+"";
	}
	
	@GetMapping("/recipes")
	//@ResponseBody
	public String getRecipes(Model uiModel, @RequestParam("page.page") int pageStart) {
        PageWrapper<Recipe> page = new PageWrapper<Recipe>(recipeRepository.findAll(PageRequest.of(pageStart, 5)),"/recipes");
        //uiModel.addAttribute("recipes", recipeRepository.findAll(
        	//			Sort.by("id").ascending()
				//		.and(Sort.by("respDscrptn"))
				
				//)
		//);
        uiModel.addAttribute("page", page);
        return "prevNext";
    }
	
	@RequestMapping(value= {"","/","index"}, method = RequestMethod.GET)
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.getAllRecipe());
		return "index" ;
	}
	@RequestMapping(value= {"/404"}, method = RequestMethod.GET)
	public void check404rPage(Model model) {
		throw new NotFoundException();
		//return "index" ;
	}
	@RequestMapping(value= {"/400"}, method = RequestMethod.GET)
	public void check400rPage(Model model) {
		throw new NumberFormatException();
		//return "index" ;
	}
}
