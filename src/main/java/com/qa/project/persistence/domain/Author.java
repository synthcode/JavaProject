package com.qa.project.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author")
public class Author {
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long bookId;
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
	public Author(Long id, Long bookId, String firstName, String middleName, String lastName) {
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
			boolean idEqual = this.id.equals(author.id);   // NOT NULL
			
			boolean bookIdEqual;
			if (this.bookId == null && author.bookId == null) bookIdEqual = true;
			else if (this.bookId == null || author.bookId == null) bookIdEqual = false;
			else bookIdEqual = this.bookId.equals(author.bookId);
			
			boolean firstNameEqual = this.firstName.equals(author.firstName);   // NOT NULL
			
			boolean middleNameEqual;
			if (this.middleName == null && author.middleName == null) middleNameEqual = true;
			else if (this.middleName == null || author.middleName == null) middleNameEqual = false;
			else middleNameEqual = this.middleName.equals(author.middleName);
			
			boolean lastNameEqual = this.lastName.equals(author.lastName);   // NOT NULL
			return idEqual && bookIdEqual && firstNameEqual && middleNameEqual && lastNameEqual;
		}
	}
	
	// (Override public int hashCode())
}
