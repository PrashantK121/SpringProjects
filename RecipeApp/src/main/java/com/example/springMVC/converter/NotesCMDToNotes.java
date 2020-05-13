package com.example.springMVC.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.example.springMVC.cmd.NotesCMD;
import com.example.springMVC.model.Notes;

import lombok.Synchronized;

/**
 * Created By Prince for Project RecipeApp on Apr 26, 2020
 *
 */
@Component
public class NotesCMDToNotes implements Converter<NotesCMD, Notes> {

	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCMD source) {
		if(source == null)
		return null;
		
		final Notes notes = new Notes();
		notes.setId(source.getId());
		notes.setRecipeNotes(source.getRecipeNotes());
		return  notes;
		
	}

}
