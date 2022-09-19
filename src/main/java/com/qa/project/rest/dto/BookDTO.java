package com.qa.project.rest.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.qa.project.persistence.domain.Author;
import com.qa.project.persistence.domain.Publisher;
import com.qa.project.persistence.domain.Stock;

public class BookDTO {
	private Long id;
	private String title;
	private Long ISBN;
	private LocalDate publicationDate;   // yyyy-MM-dd
	
	// other tables
	private Set<Author> authors = new HashSet<>();
	private Publisher publisher;
	private Stock stock;
	
	// Default Constructor
	public BookDTO() {
		//
	}

	// Constructor (with id, without authors, publisher, stock)
	public BookDTO(Long id, String title, Long ISBN, LocalDate publicationDate) {
		this.id = id;
		this.title = title;
		this.ISBN = ISBN;
		this.publicationDate = publicationDate;
	}
		
	// Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	// Getters and setters (for other tables)
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
}
