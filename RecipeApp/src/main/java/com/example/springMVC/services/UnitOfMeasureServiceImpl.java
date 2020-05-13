package com.example.springMVC.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.example.springMVC.cmd.UnitOfMeasureCMD;
import com.example.springMVC.converter.UnitOfMesureToUnitOfMeasureCMD;
import com.example.springMVC.repository.UnitOfMeasureRepository;

/**
 * Created By Prince for Project RecipeApp on Apr 27, 2020
 *
 */
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private UnitOfMeasureRepository uomrepository ;
	private UnitOfMesureToUnitOfMeasureCMD uomtoCMD ;
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomrepository, UnitOfMesureToUnitOfMeasureCMD uomtoCMD) {
		this.uomrepository = uomrepository;
		this.uomtoCMD = uomtoCMD;
	}

	@Override
	public Set<UnitOfMeasureCMD> listAllUoms() {
		return StreamSupport.stream(uomrepository.findAll()
                .spliterator(), false)
                .map(uomtoCMD::convert)
                .collect(Collectors.toSet());
	}

}
