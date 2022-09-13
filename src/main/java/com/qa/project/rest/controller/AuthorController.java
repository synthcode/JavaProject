package com.qa.project.rest.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.project.persistence.domain.Author;
import com.qa.project.rest.dto.AuthorDTO;
import com.qa.project.service.AuthorService;

@RestController
public class AuthorController {
	private AuthorService service;
	
	public AuthorController(AuthorService service) {
		this.service = service;
	}

	// Test
	@GetMapping("/author/test")
    public String test() {
        return "Hello, World! from Author Controller";
    }
	
	// Create
    @PostMapping("/author/create")
    public AuthorDTO addAuthor(@RequestBody Author author) {
        return this.service.addAuthor(author);
    }
    
	// Read
    @GetMapping("/author/getAll")
    public List<AuthorDTO> getAllAuthors() {
        return this.service.getAllAuthors();
    }

    // Update
    @PutMapping("/author/update")
    public AuthorDTO updateAuthor(@PathParam("id") Long id, @RequestBody Author author) {
        return this.service.updateAuthor(id, author);
    }
    
    // Delete
    @DeleteMapping("/author/delete/{id}")
    public boolean removeAuthor(@PathVariable Long id) {
        return this.service.removeAuthor(id);
    }
}
