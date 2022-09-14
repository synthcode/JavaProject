package com.qa.project.rest.dto;

public class AuthorDTO {
	private Long id;
	private Long bookId;
	private String firstName;
	private String middleName;
	private String lastName;

	// Default Constructor
	public AuthorDTO() {
		//
	}
	
	// Constructor (with id)
	public AuthorDTO(Long id, Long bookId, String firstName, String middleName, String lastName) {
		this.id = id;
		this.bookId = bookId;
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
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
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
}
