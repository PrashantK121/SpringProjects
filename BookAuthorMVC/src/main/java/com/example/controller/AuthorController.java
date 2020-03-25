package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.repository.AuthorRepository;

@Controller
public class AuthorController {
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@RequestMapping(value="authors", method = RequestMethod.GET)
	public String getAuthors(Model model) {
		model.addAttribute("Authors", authorRepository.findById(29L).get());
		return "Author" ;
	}
}
