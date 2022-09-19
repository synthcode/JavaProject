package com.qa.project.persistence.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = "authors")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(unique = true)
	private Long ISBN;
	@Column
	private LocalDate publicationDate;   // yyyy-MM-dd
	
	// @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
	@ManyToMany(mappedBy = "books",
			      cascade = {CascadeType.PERSIST, CascadeType.MERGE,
	                           CascadeType.REFRESH, CascadeType.DETACH})
	private Set<Author> authors = new HashSet<>();
	
	@ManyToOne
	private Publisher publisher;
	
    @OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private Stock stock;
	
	// Default Constructor
	public Book() {
		//
	}
	
	// Constructor (with id, without authors, publisher, stock)
	public Book(Long id, String title, Long ISBN, LocalDate publicationDate) {
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
		// To avoid error "attempted to assign id from null one-to-one property":
		this.stock.setBook(this);
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
			Book book = (Book) obj;
			boolean idEqual = this.id.equals(book.id);   // NOT NULL
			boolean titleEqual = this.title.equals(book.title);   // NOT NULL
			
			boolean ISBNEqual;
			if (this.ISBN == null && book.ISBN == null) ISBNEqual = true;
			else if (this.ISBN == null || book.ISBN == null) ISBNEqual = false;
			else ISBNEqual = this.ISBN.equals(book.ISBN);
			
			boolean publicationDateEqual;
			if (this.publicationDate == null && book.publicationDate== null) publicationDateEqual = true;
			else if (this.publicationDate == null || book.publicationDate== null) publicationDateEqual = false;
			else publicationDateEqual = this.publicationDate.equals(book.publicationDate);
			
			return idEqual && titleEqual && ISBNEqual && publicationDateEqual;
		}
	}
	
//	@Override
//	public int hashCode() {
//		return this.getId().hashCode();
//  }
	@Override	
    public int hashCode() {
		return this.getISBN().hashCode();
    }
}