package com.example.springMVC.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.springMVC.cmd.UnitOfMeasureCMD;
import com.example.springMVC.model.UnitOfMeasure;

import lombok.Synchronized;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Component
public class UnitOfMesureCMDToUnitOfMeasure implements Converter<UnitOfMeasureCMD, UnitOfMeasure>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasure convert(UnitOfMeasureCMD source) {
		if(source == null)
		return null;
		
		final UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(source.getId());
		uom.setUomDscrptn(source.getUomDscrptn());
		return uom ;
		
	}
	
}
