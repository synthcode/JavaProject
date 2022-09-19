package com.qa.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.project.exception.BookNotFoundException;
import com.qa.project.persistence.domain.Author;
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
   	 	// Optional<Book> optionalBook = this.repo.findById(id);
   	 	// return optionalBook.get();
   	    return this.repo.findById(id).orElseThrow(BookNotFoundException::new);
    }
    public BookDTO getBookById(Long id) {
    	return this.mapBookToDTO(getBookByIdRepo(id));
    }
    
	// Read by title key word
    protected List<Book> getBookByTitleKeyWordRepo(String keyWord) {
    	return this.repo.findByTitleContaining(keyWord);
    }
    public List<BookDTO> getBookByTitleKeyWord(String keyWord) {
    	return getBookByTitleKeyWordRepo(keyWord).stream().map(this::mapBookToDTO).collect(Collectors.toList());
    }
    
    // Read books of publisher
    protected List<Book> getBooksOfPublisherRepo(Long publisherId) {
    	return this.repo.customFindBooksOfPublisher(publisherId);
    }
    public List<BookDTO> getBooksOfPublisher(Long publisherId) {
    	return getBooksOfPublisherRepo(publisherId).stream().map(this::mapBookToDTO).collect(Collectors.toList());
    }
    
    // Read books of author
    protected List<Book> getBooksOfAuthorRepo(Long authorId) {
    	return this.repo.customFindBooksOfAuthor(authorId);
    }
    public List<BookDTO> getBooksOfAuthor(Long authorId) {
    	return getBooksOfAuthorRepo(authorId).stream().map(this::mapBookToDTO).collect(Collectors.toList());
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
         
         // Not setting any authors here
         existing.setPublisher(updatedBook.getPublisher());
         existing.setStock(updatedBook.getStock());

         return this.repo.save(existing);
    }
    public BookDTO updateBook(Long id, Book updatedBook) {
    	return this.mapBookToDTO(updateBookRepo(id, updatedBook));
    }
    
	// Add Author
    public void addAuthorToBook(Long id, Long authorId) {
    	this.repo.customAddAuthorToBook(id, authorId);
    }
    
    // Delete author
    public void deleteAuthorFromBook(Long id, Long authorId) {
    	this.repo.customDeleteAuthorFromBook(id, authorId);
    }

    // Delete
    public boolean removeBook(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}
