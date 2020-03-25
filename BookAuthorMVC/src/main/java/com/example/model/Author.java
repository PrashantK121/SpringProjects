package com.example.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorSeqGen")
	@SequenceGenerator(name="authorSeqGen", sequenceName = "authorSequence", initialValue = 1, allocationSize = 1)
	protected Long authorID;
	protected String name;
	@OneToOne(fetch = FetchType.EAGER,
	        cascade = {
	                CascadeType.MERGE,
	                CascadeType.PERSIST
	            })
	protected  Address address;
	protected String PhoneNumber;  
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "authorID")
	protected List<Book> books;
	public Long getAuthorID() {
		return authorID;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public void setAuthorID(Long authorID) {
		this.authorID = authorID;
	}

	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
