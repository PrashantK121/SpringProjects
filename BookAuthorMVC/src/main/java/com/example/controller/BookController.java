package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Book;
import com.example.repository.BookRepository;
@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@RequestMapping(value="books", method = RequestMethod.GET)
	public String getAuthors(Model model) {
		List<Book> books = bookRepository.findAll();
		//Optional<Book> book = bookRepository.findById(58L);
		////model.addAttribute("books",book.get() );
		model.addAttribute("books",books );
		return "Book" ;
	}
}
