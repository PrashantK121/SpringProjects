package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSeqGenerator")
	@SequenceGenerator(name = "bookSeqGenerator" , sequenceName = "BookSequence",  initialValue = 1, allocationSize = 1)
	public Long bookID;
	public String title;
	public String publisher;
	
	public Long getBookID() {
		return bookID;
	}
	public void setBookID(Long bookID) {
		this.bookID = bookID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", title=" + title + ", publisher=" + publisher + "]";
	}
	
	
	
}
