package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.model.Address;
import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.AddressRepository;
import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;

@Component
public class DataInitializerStartup implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private AuthorRepository authorRepository ;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AddressRepository addressRepository;
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		//initData();
		
	}
	public void initData () {
		Address address1 = new Address();
		Address address2 = new Address();
		Book book1 = new Book();
		Book book2 = new Book();
		Book book3 = new Book();
		List<Book> bookLst = new ArrayList<Book>();
		Author author1 = new Author();
		Author author2 = new Author();
		
		
		address1.setCity(new StringBuffer("Mahoba"));
		address1.setHouseNum(new StringBuffer("33/21"));
		address1.setPincode(210427);
		address1.setState(new StringBuffer("UP"));
		address1.setStreet(new StringBuffer("Alha Chow Gandhi Nagar"));
		address2.setCity(new StringBuffer("Gurgaon"));
		address2.setHouseNum(new StringBuffer("242/21"));
		address2.setPincode(122001);
		address2.setState(new StringBuffer("HR"));
		address2.setStreet(new StringBuffer("Gali number 3"));
		
		
		book1.setTitle("ABC");
		book1.setPublisher("TTCX");
		book2.setTitle("XYZ");
		book2.setPublisher("KJUH");
		book3.setTitle("PQRS");
		book3.setPublisher("WEDR");
		bookLst.add(book1);
		bookLst.add(book2);
		bookLst.add(book3);
		
		author1.setAddress(address1);
		author1.setName("Prashant");
		author1.setPhoneNumber("7358302754");
		author1.setBooks(bookLst);
		
		//author2.setAddress(address2);
		//author2.setName("Prince");
		//author2.setPhoneNumber("8993635245");
		//author2.setBooks(bookLst);
		authorRepository.save(author1);
		//authorRepository.save(author2);
		//addressRepository.save(address1);
		//addressRepository.save(address2);
		//bookRepository.save(book1);
		//bookRepository.save(book2);
		//bookRepository.save(book3);
		
	}
	

}
