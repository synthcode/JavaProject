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

import com.qa.project.persistence.domain.Publisher;
import com.qa.project.rest.dto.PublisherDTO;
import com.qa.project.service.PublisherService;

@RestController
public class PublisherController {
    private PublisherService service;

    public PublisherController(PublisherService service) {
        this.service = service;
    }
	
    // Test
	@GetMapping("/publisher/test")
    public String test() {
        return "Hello, World! from Publisher Controller";
    }
	
	// Create
    @PostMapping("/publisher/create")
    public PublisherDTO addPublisher(@RequestBody Publisher publisher) {
        return this.service.addPublisher(publisher);
    }
    
	// Read by id
    @GetMapping("/publisher/get")
    public PublisherDTO getPublisherById(@PathParam("id") Long id) {
    	return this.service.getPublisherById(id);
    }
    
	// Read all
    @GetMapping("/publisher/getAll")
    public List<PublisherDTO> getAllPublishers() {
        return this.service.getAllPublishers();
    }
    
    // Update
    @PutMapping("/publisher/update")
    public PublisherDTO updatePublisher(@PathParam("id") Long id, @RequestBody Publisher publisher) {
        return this.service.updatePublisher(id, publisher);
    }
    
    // Delete
    @DeleteMapping("/publisher/delete/{id}")
    public boolean removePublisher(@PathVariable Long id) {
        return this.service.removePublisher(id);
    }
}