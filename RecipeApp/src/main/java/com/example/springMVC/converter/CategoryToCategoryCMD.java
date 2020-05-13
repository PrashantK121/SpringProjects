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
public class CategoryToCategoryCMD implements Converter<Category, CategoryCMD> {

	@Synchronized
	@Nullable
	@Override
	public CategoryCMD convert(Category source) {
		if(source == null)
		return null;
		
		final CategoryCMD categoryCMD = new CategoryCMD();
		categoryCMD.setId(source.getId());
		categoryCMD.setCtgryDscrptn(source.getCtgryDscrptn());
		return categoryCMD;
	}
	
}
