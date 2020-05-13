package com.example.springMVC.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springMVC.model.UnitOfMeasure;

/**
 * Created By Prince for Project RecipeApp on Apr 13, 2020
 *
 */
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {
	Optional<UnitOfMeasure> findByUomDscrptn(String uomDscrptn);
	
}
