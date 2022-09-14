package com.qa.project.rest.dto;

import java.time.LocalDate;

public class BookDTO {
	private Long id;
	private String title;
	private Long ISBN;
	private LocalDate publicationDate;   // yyyy-MM-dd
	
	// Default Constructor
	public BookDTO() {
		//
	}

	// Constructor (with id)
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
}
