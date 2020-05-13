package com.example.springMVC.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
public class Recipe {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "RecipeSeqGenerator")
	@SequenceGenerator(name = "RecipeSeqGenerator", sequenceName = "RECIPE_SEQ", allocationSize = 1 )
	private Long id;
	private String respDscrptn;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	
	@Lob
	private String directions ;
	
	@OneToMany(mappedBy="recipe", cascade = CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	@Lob
	private Byte[] image ;
	
	@Enumerated(value = EnumType.STRING)
	private Difficulity difficulity ;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes ;
	
	@ManyToMany
	 @JoinTable(name = "RECIPE_CATEGORY",
	 joinColumns=@JoinColumn(name = "recipe_id"),
	 inverseJoinColumns = @JoinColumn(name="category_id")
	 )
	private Set<Category> categories = new HashSet<Category>();
	
	public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }
	}
    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
