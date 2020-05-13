package com.example.springMVC.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE ,generator = "notesSeqGenerator")
	@SequenceGenerator(name = "notesSeqGenerator", sequenceName = "NOTES_SEQ", allocationSize = 1)
	private Long id;
	@OneToOne
	private Recipe recipe ;
	@Lob
	private String recipeNotes ;
	@Override
	public String toString() {
		return "Notes [id=" + id + ", recipeNotes=" + recipeNotes + "]";
	}
	
}
