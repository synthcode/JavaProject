package com.qa.project.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Author {
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorId;
	@Column(nullable = false)
	private String firstName;
	@Column
	private String middleName;
	@Column(nullable = false)
	private String lastName;

	// Default Constructor
	public Author() {
		//
	}
	
	// Constructor (with id)
	public Author(Long authorId, String firstName, String middleName, String lastName) {
		this.authorId = authorId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj.getClass() != this.getClass()) {
			return false;
		} else if (obj == this) {
			return true;
		} else {
			Author author = (Author) obj;
			boolean authorIdEqual = this.authorId.equals(author.authorId);   // NOT NULL
			boolean firstNameEqual = this.firstName.equals(author.firstName);   // NOT NULL
			
			boolean middleNameEqual;
			if (this.middleName == null && author.middleName == null) middleNameEqual = true;
			else if (this.middleName == null || author.middleName == null) middleNameEqual = false;
			else middleNameEqual = this.middleName.equals(author.middleName);
			
			boolean lastNameEqual = this.lastName.equals(author.lastName);   // NOT NULL
			return authorIdEqual && firstNameEqual && middleNameEqual && lastNameEqual;
		}
	}
	
	// (Override public int hashCode())
}
