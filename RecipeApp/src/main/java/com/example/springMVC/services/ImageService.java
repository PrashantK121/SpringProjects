package com.example.springMVC.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.example.springMVC.model.Recipe;

/**
 * Created By Prince for Project RecipeApp on Apr 27, 2020
 *
 */
public interface ImageService {
	void saveImageFile(Long recipeId, MultipartFile file);
	
}
