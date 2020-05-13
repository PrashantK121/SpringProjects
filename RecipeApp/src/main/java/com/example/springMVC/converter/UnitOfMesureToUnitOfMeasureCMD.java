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
public class UnitOfMesureToUnitOfMeasureCMD implements Converter<UnitOfMeasure, UnitOfMeasureCMD>{

	@Synchronized
	@Nullable
	@Override
	public UnitOfMeasureCMD convert(UnitOfMeasure source) {
		if(source == null)
		return null;
		
		final UnitOfMeasureCMD uomCMD = new UnitOfMeasureCMD();
		uomCMD.setId(source.getId());
		uomCMD.setUomDscrptn(source.getUomDscrptn());
		return uomCMD;
	}

}
