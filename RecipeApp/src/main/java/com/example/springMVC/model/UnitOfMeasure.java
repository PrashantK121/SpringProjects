package com.example.springMVC.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

/**
 * Created By Prince for Project RecipeApp on Apr 12, 2020
 *
 */
@Setter
@Getter
@Entity
public class UnitOfMeasure {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "uomSeqGenerator")
	@SequenceGenerator(name = "uomSeqGenerator", sequenceName = "UOM_SEQ", allocationSize = 1)
	private Long id;
	private String uomDscrptn;
}
