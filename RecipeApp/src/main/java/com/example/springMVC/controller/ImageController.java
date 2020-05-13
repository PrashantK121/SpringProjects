package com.example.springMVC.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.springMVC.cmd.RecipeCMD;
import com.example.springMVC.services.ImageService;
import com.example.springMVC.services.RecipeService;

/**
 * Created By Prince for Project RecipeApp on Apr 28, 2020
 *
 */
@Controller
public class ImageController {
	private ImageService imageService;
	private RecipeService recipeService;
	
	public ImageController(ImageService imageService, RecipeService recipeService) {
		this.imageService = imageService;
		this.recipeService = recipeService;
	}
	
	@GetMapping("recipe/{id}/image")
	public String showImageUploadForm(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/imageUploadForm";
	}
	
	@PostMapping("recipe/{id}/image")
	public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile  imageFile ) {
		imageService.saveImageFile(Long.valueOf(id), imageFile);
		return "redirect:/recipe/"+id+"/show";
	}
	
	@GetMapping("recipe/{id}/recipeimage")
	public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
		RecipeCMD recipeCMD = recipeService.findCommandById(Long.valueOf(id));
		if(recipeCMD.getImage()!=null) {
			byte[] byteArray = new byte[recipeCMD.getImage().length];
			int i=0;
			for(Byte byteWrapped : recipeCMD.getImage()) {
				byteArray[i++] = byteWrapped ;
			}
			response.setContentType("image/jpeg");
			InputStream is = new ByteArrayInputStream(byteArray);
			IOUtils.copy(is, response.getOutputStream());
		}
	}
}
