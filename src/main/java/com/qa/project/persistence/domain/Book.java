package com.qa.project.persistence.domain;

import java.sql.Date;

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
	private Date publicationDate;
	
	// Default Constructor
	public Book() {
		//
	}
	
	// Constructor (without id)
	public Book(String title, Long ISBN, Date publicationDate) {
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