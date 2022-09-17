package com.qa.project.persistence.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "publisher")
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
	private List<Book> books;
	
	// Default Constructor
	public Publisher() {
		//
	}
	
	// Constructor (with id)
	public Publisher(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj.getClass() != this.getClass()) {
			return false;
		} else if (obj == this) {
			return true;
		} else {
			Publisher publisher = (Publisher) obj;
			boolean idEqual = this.id.equals(publisher.id);   // NOT NULL
			
			boolean nameEqual;
			if (this.name == null && publisher.name == null) nameEqual = true;
			else if (this.name == null || publisher.name == null) nameEqual = false;
			else nameEqual = this.name.equals(publisher.name);
			
			return idEqual && nameEqual;
		}
	}
	
	// (Override public int hashCode())
}
