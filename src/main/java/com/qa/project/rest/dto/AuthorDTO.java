package com.qa.project.rest.dto;

import java.util.HashSet;
import java.util.Set;

import com.qa.project.persistence.domain.Book;

public class AuthorDTO {
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	
	// other tables
	private Set<Book> books = new HashSet<>();

	// Default Constructor
	public AuthorDTO() {
		//
	}
	
	// Constructor (with id, without books)
	public AuthorDTO(Long id, String firstName, String middleName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	// Getters and setters (for other tables)
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
}
