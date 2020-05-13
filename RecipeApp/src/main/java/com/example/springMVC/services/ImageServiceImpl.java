package com.example.springMVC.services;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.springMVC.model.Recipe;
import com.example.springMVC.repository.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Created By Prince for Project RecipeApp on Apr 27, 2020
 *
 */
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	private RecipeRepository recipeRepository ;
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		try {
			Recipe recipe = recipeRepository.findById(recipeId).get();
			Byte[] imageBytes = new Byte[file.getBytes().length];
			int i =0 ;
			for(byte b : file.getBytes()) {
				imageBytes[i++] = b ;
			}
			recipe.setImage(imageBytes);
			recipeRepository.save(recipe);
		}catch(IOException ex) {
			log.error("Error in uploading file : "+ex );
			ex.printStackTrace();
		}

	}

	

}
