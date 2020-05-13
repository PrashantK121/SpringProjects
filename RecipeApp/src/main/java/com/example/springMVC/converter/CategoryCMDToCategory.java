package com.example.springMVC.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.springMVC.cmd.CategoryCMD;
import com.example.springMVC.model.Category;

import lombok.Synchronized;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Component
public class CategoryCMDToCategory implements Converter<CategoryCMD, Category> {

	@Synchronized
	@Nullable
	@Override
	public Category convert(CategoryCMD source) {
		final Category category = new Category();
		if(source == null)
			return null;
		category.setId(source.getId());
		category.setCtgryDscrptn(source.getCtgryDscrptn());
		
		return category;
	}
	
}
