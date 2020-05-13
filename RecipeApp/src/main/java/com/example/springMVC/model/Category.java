package com.example.springMVC.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CategorySeqGenerator")
	@SequenceGenerator(name = "CategorySeqGenerator", sequenceName = "CTGRY_SEQ", allocationSize = 1 )
	private Long id ;
    private String ctgryDscrptn ;
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes ;
	
    @Override
	public String toString() {
		return "Category [id=" + id + ", ctgryDscrptn=" + ctgryDscrptn + "]";
	}
    
    
}
