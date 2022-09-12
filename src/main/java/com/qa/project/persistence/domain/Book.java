package com.qa.project.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// import java.util.Arrays;

@Entity
public class Book {
	// Constants
	// static final int MAX_AUTHORS = 4;
	
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String title;
	@Column
	private String author;
	// private String[] authors;
	
	// Default Constructor
	public Book() {
		//
	}
	
	// Constructor (without id)
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}
	
	/*
	public Book(String title, String[] authors) {
		if (authors == null) {
			// System.out.println("Create book failed! No authors provided");
		}
		else if (authors.length <= MAX_AUTHORS) {
			this.title = title;
            this.authors = authors;
            // System.out.println("Create book success!");
        } else {
            // System.out.println("Create book failed! Maximum number of authors is " + MAX_AUTHORS);
        }
	}
	*/

	// Getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

	/*
	public String getAuthors() {
		return Arrays.toString(authors);   // from java.util.Arrays
	}
	
	public void setAuthors(String[] authors) {
		if (authors == null) {
			// System.out.println("Set authors failed! No authors provided");
		}
		else if (authors.length <= MAX_AUTHORS) {
            this.authors = authors;
        } else {
            // System.out.println("Set authors failed! Maximum number of authors is " + MAX_AUTHORS);
        }
    }
    */
}