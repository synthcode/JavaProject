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

import com.qa.project.persistence.domain.Book;
import com.qa.project.rest.dto.BookDTO;
import com.qa.project.service.BookService;

@RestController
public class BookController {
    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
	
	@GetMapping("/test")
    public String test() {
        return "Hello, World!";
    }
	
	// Create
    @PostMapping("/create")
    public BookDTO addBook(@RequestBody Book book) {
        return this.service.addBook(book);
    }
    
	// Read
    @GetMapping("/getAll")
    public List<BookDTO> getAllBooks() {
        return this.service.getAllBooks();
    }

    // Update
    @PutMapping("/update")
    public BookDTO updateBook(@PathParam("id") long id, @RequestBody Book book) {
        return this.service.updateBook(id, book);
    }
    
    // Delete
    @DeleteMapping("/delete/{id}")
    public boolean removeBook(@PathVariable long id) {
        return this.service.removeBook(id);
    }
}
