package com.qa.project.rest.dto;

import com.qa.project.persistence.domain.Book;

public class StockDTO {
	private Long id;
	private Integer quantity;
	
	// other tables
	private Book book;
	
	// Default Constructor
	public StockDTO() {
		//
	}

	// Constructor (with id, without book)
	public StockDTO(Long id, Integer quantity) {
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
}
