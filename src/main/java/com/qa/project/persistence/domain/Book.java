package com.qa.project.persistence.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	@Column(nullable = false)
	private String title;
	@Column(unique = true)
	private Long ISBN;
	@Column
	private LocalDate publicationDate;   // yyyy-MM-dd
	
	// Default Constructor
	public Book() {
		//
	}
	
	// Constructor (with id)
	public Book(Long bookId, String title, Long ISBN, LocalDate publicationDate) {
		this.bookId = bookId;
		this.title = title;
		this.ISBN = ISBN;
		this.publicationDate = publicationDate;
	}
	
	// Getters and setters
	public long getId() {
		return bookId;
	}
	public void setId(Long bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getISBN() {
		return ISBN;
	}
	public void setISBN(Long ISBN) {
		this.ISBN = ISBN;
	}
	public LocalDate getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj.getClass() != this.getClass()) {
			return false;
		} else if (obj == this) {
			return true;
		} else {
			Book book = (Book) obj;
			boolean bookIdEqual = this.bookId.equals(book.bookId);   // NOT NULL
			boolean titleEqual = this.title.equals(book.title);   // NOT NULL
			
			boolean ISBNEqual;
			if (this.ISBN == null && book.ISBN == null) ISBNEqual = true;
			else if (this.ISBN == null || book.ISBN == null) ISBNEqual = false;
			else ISBNEqual = this.ISBN.equals(book.ISBN);
			
			boolean publicationDateEqual;
			if (this.publicationDate == null && book.publicationDate== null) publicationDateEqual = true;
			else if (this.publicationDate == null || book.publicationDate== null) publicationDateEqual = false;
			else publicationDateEqual = this.publicationDate.equals(book.publicationDate);
			
			return bookIdEqual && titleEqual && ISBNEqual && publicationDateEqual;
		}
	}
	
	// (Override public int hashCode())
}