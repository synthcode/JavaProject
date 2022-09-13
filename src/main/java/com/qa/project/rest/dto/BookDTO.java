package com.qa.project.rest.dto;

import java.sql.Date;

public class BookDTO {
	private Long bookId;
	private String title;
	private Long ISBN;
	private Date publicationDate;
	
	public BookDTO() {
		// super();
	}
	
	// Constructor (without id?)
	public BookDTO(String title, Long ISBN, Date publicationDate) {
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
	public Date getPublicationDate() {
		return publicationDate;
	}
	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
}
