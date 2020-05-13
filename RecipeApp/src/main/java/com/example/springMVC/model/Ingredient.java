package com.example.springMVC.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created By Prince for Project RecipeApp on Apr 12, 2020
 *
 */
@Setter
@Getter
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IngrdntSeqGenerator")
	@SequenceGenerator(name="IngrdntSeqGenerator", sequenceName = "INGRDNT_SEQ", allocationSize = 1)
	private Long id ;
    private String indgrntsDscrptn ;
    private BigDecimal amnt ;
    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom ;
    @ManyToOne
    private Recipe recipe ;  
    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.indgrntsDscrptn = description;
        this.amnt = amount;
        this.uom = uom;
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.indgrntsDscrptn = description;
        this.amnt = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", indgrntsDscrptn=" + indgrntsDscrptn + ", amnt=" + amnt + ", uom=" + uom
				+ "]";
	}
    
}
