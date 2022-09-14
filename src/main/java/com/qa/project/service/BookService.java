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
    
    // private
    private BookDTO mapBookToDTO(Book book) {
        return this.mapper.map(book, BookDTO.class);
    }
    
    // Create
    protected Book addBookRepo(Book book) {
    	return this.repo.save(book);
    }
    public BookDTO addBook(Book book) {
    	return this.mapBookToDTO(addBookRepo(book));
    }
    
	// Read by id
    protected Book getBookByIdRepo(Long id) {
   	 	Optional<Book> optionalBook = this.repo.findById(id);
   	 	return optionalBook.get();
    }
    public BookDTO getBookById(Long id) {
    	return this.mapBookToDTO(getBookByIdRepo(id));
    }
	
	// Read all
    protected List<Book> getAllBooksRepo() {
    	return this.repo.findAll();
    }
    public List<BookDTO> getAllBooks() {
    	return getAllBooksRepo().stream().map(this::mapBookToDTO).collect(Collectors.toList());
    }
   
	// Update
    protected Book updateBookRepo(Long id, Book updatedBook) {
    	 Optional<Book> existingOptional = this.repo.findById(id);
         Book existing = existingOptional.get();

         existing.setTitle(updatedBook.getTitle());
         existing.setISBN(updatedBook.getISBN());
         existing.setPublicationDate(updatedBook.getPublicationDate());

         return this.repo.save(existing);
    }
    public BookDTO updateBook(Long id, Book updatedBook) {
    	return this.mapBookToDTO(updateBookRepo(id, updatedBook));
    }

    // Delete
    public boolean removeBook(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}
