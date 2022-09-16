package com.qa.project.rest.dto;

public class StockDTO {
	private Long id;
	private Integer quantity;
	
	// Default Constructor
	public StockDTO() {
		//
	}

	// Constructor (with id)
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
}
