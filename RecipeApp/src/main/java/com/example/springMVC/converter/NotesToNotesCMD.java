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
public class NotesToNotesCMD implements Converter<Notes, NotesCMD>{

	@Synchronized
	@Nullable
	@Override
	public NotesCMD convert(Notes source) {
		if(source == null )
		return null;
		
		final NotesCMD notesCMD =  new NotesCMD();
		notesCMD.setId(source.getId());
		notesCMD.setRecipeNotes(source.getRecipeNotes());
		return notesCMD;
	}

}
