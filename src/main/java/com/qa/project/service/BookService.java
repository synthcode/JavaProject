package com.qa.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.project.persistence.domain.Book;
import com.qa.project.persistence.repository.BookRepository;
import com.qa.project.rest.dto.BookDTO;

@Service
public class BookService {
	private BookRepository repo;
	private ModelMapper mapper;

    public BookService(BookRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    
    private BookDTO mapBookToDTO(Book book) {
        return this.mapper.map(book, BookDTO.class);
    }
    
    // Create
    public BookDTO addBook(Book book) {
    	Book saved = this.repo.save(book);
        return this.mapBookToDTO(saved);
    }
	
	// Read
    public List<BookDTO> getAllBooks() {
    	return this.repo.findAll().stream()
    			.map(this::mapBookToDTO).collect(Collectors.toList());
    }

	// Update
    public BookDTO updateBook(long id, Book newBook) {
        Optional<Book> existingOptional = this.repo.findById(id);
        Book existing = existingOptional.get();

        existing.setTitle(newBook.getTitle());
        existing.setAuthor(newBook.getAuthor());

        Book updated = this.repo.save(existing);
        return this.mapBookToDTO(updated);
    }

    // Delete
    public boolean removeBook(long id) {
        // removes the entity
        this.repo.deleteById(id);
        // checks to see if it still exists
        boolean exists = this.repo.existsById(id);
        // returns true if entity no longer exists
        return !exists;
    }
}
