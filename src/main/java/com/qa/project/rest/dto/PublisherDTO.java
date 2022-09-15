package com.qa.project.rest.dto;

public class PublisherDTO {
	private Long id;
	private String name;
	
	// Default Constructor
	public PublisherDTO() {
		//
	}

	// Constructor (with id)
	public PublisherDTO(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	// Getters and setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
