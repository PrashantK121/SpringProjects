package com.example.springMVC.services;

import java.util.Set;

import com.example.springMVC.cmd.UnitOfMeasureCMD;

/**
 * Created By Prince for Project RecipeApp on Apr 27, 2020
 *
 */
public interface UnitOfMeasureService {
	Set<UnitOfMeasureCMD> listAllUoms();
}
