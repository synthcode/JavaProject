package com.qa.project.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.qa.project.persistence.domain.Book;

public class PublisherDTO {
	private Long id;
	private String name;
	
	// other tables
	private List<Book> books = new ArrayList<>();
	
	// Default Constructor
	public PublisherDTO() {
		//
	}

	// Constructor (with id, without books)
	public PublisherDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// Getters and setters (for other tables)
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
