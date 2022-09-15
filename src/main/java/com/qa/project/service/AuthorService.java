package com.qa.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.project.persistence.domain.Author;
import com.qa.project.persistence.repository.AuthorRepository;
import com.qa.project.rest.dto.AuthorDTO;

@Service
public class AuthorService {
	private AuthorRepository repo;
	private ModelMapper mapper;

    public AuthorService(AuthorRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    
    // protected
    public AuthorDTO mapAuthorToDTO(Author author) {
        return this.mapper.map(author, AuthorDTO.class);
    }
    
    // Create
    protected Author addAuthorRepo(Author author) {
    	return this.repo.save(author);
    }
    public AuthorDTO addAuthor(Author author) {
    	return this.mapAuthorToDTO(addAuthorRepo(author));
    }
    
    // Read by id
    protected Author getAuthorByIdRepo(Long id) {
   	 	Optional<Author> optionalBook = this.repo.findById(id);
   	 	return optionalBook.get();
    }
    public AuthorDTO getAuthorById(Long id) {
    	return this.mapAuthorToDTO(getAuthorByIdRepo(id));
    }
    
    // Read by last name
    protected List<Author> getAuthorByLastNameRepo(String lastName) {
    	return this.repo.findByLastName(lastName);
    }
    public List<AuthorDTO> getAuthorByLastName(String lastName) {
    	return getAuthorByLastNameRepo(lastName).stream().map(this::mapAuthorToDTO).collect(Collectors.toList());
    }
    
    // Read authors of book
    protected List<Author> getAuthorsOfBookRepo(Long BookId) {
    	return this.repo.customFindAuthorsOfBook(BookId);
    }
    public List<AuthorDTO> getAuthorsOfBook(Long BookId) {
    	return getAuthorsOfBookRepo(BookId).stream().map(this::mapAuthorToDTO).collect(Collectors.toList());
    }
	
	// Read all
    protected List<Author> getAllAuthorsRepo() {
    	return this.repo.findAll();
    }
    public List<AuthorDTO> getAllAuthors() {
    	return getAllAuthorsRepo().stream().map(this::mapAuthorToDTO).collect(Collectors.toList());
    }

	// Update
    protected Author updateAuthorRepo(Long id, Author updatedAuthor) {
        Optional<Author> existingOptional = this.repo.findById(id);
        Author existing = existingOptional.get();

        existing.setFirstName(updatedAuthor.getFirstName());
        existing.setMiddleName(updatedAuthor.getMiddleName());
        existing.setLastName(updatedAuthor.getLastName());

        return this.repo.save(existing);
    }
    public AuthorDTO updateAuthor(Long id, Author updatedAuthor) {
    	return this.mapAuthorToDTO(updateAuthorRepo(id, updatedAuthor));
    }
    
    // Delete
    public boolean removeAuthor(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}
