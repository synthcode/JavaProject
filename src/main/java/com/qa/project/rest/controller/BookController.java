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
	
    // Test
	@GetMapping("/book/test")
    public String test() {
        return "Hello, World! from Book Controller";
    }
	
	// Create
    @PostMapping("/book/create")
    public BookDTO addBook(@RequestBody Book book) {
        return this.service.addBook(book);
    }
    
	// Read by id
    @GetMapping("/book/get")
    public BookDTO getBookById(@PathParam("id") Long id) {
    	return this.service.getBookById(id);
    }
    
	// Read by title key word
    @GetMapping("/book/getKeyWord")
    public List<BookDTO> getBookByTitleKeyWord(@PathParam("keyWord") String keyWord) {
    	return this.service.getBookByTitleKeyWord(keyWord);
    }
    
    // Read books of publisher
    @GetMapping("/book/getPublisher")
    public List<BookDTO> getBooksOfPublisher(@PathParam("publisherId") Long publisherId) {
    	return this.service.getBooksOfPublisher(publisherId);
    }
    
    // Read books of author
    @GetMapping("/book/getAuthor")
    public List<BookDTO> getBooksOfAuthor(@PathParam("authorId") Long authorId) {
    	return this.service.getBooksOfAuthor(authorId);
    }
    
	// Read all
    @GetMapping("/book/getAll")
    public List<BookDTO> getAllBooks() {
        return this.service.getAllBooks();
    }
    
    // Update
    @PutMapping("/book/update")
    public BookDTO updateBook(@PathParam("id") Long id, @RequestBody Book book) {
        return this.service.updateBook(id, book);
    }
    
    // Delete
    @DeleteMapping("/book/delete/{id}")
    public boolean removeBook(@PathVariable Long id) {
        return this.service.removeBook(id);
    }
}
