package com.qa.project.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "stock")
public class Stock {
	@Id
	@Column(name = "book_id")
	private Long id;
	@Column
	private Integer quantity;

	@OneToOne
	@MapsId
	@JoinColumn(name = "book_id")
	@JsonBackReference
	private Book book;

	// Default Constructor
	public Stock() {
		//
	}
	
	// Constructor (with id, without book)
	public Stock(Long id, Integer quantity) {
		this.id = id;
		this.quantity = quantity;
	}
	
	// Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	// Getters and setters (for other tables)
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
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
			Stock stock = (Stock) obj;
			boolean idEqual = this.id.equals(stock.id);   // NOT NULL
			
			boolean quantityEqual;
			if (this.quantity == null && stock.quantity == null) quantityEqual = true;
			else if (this.quantity == null || stock.quantity == null) quantityEqual = false;
			else quantityEqual = this.quantity.equals(stock.quantity);
			
			return idEqual && quantityEqual;
		}
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
}
