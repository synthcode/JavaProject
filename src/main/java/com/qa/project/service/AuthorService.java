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
    
    private AuthorDTO mapAuthorToDTO(Author author) {
        return this.mapper.map(author, AuthorDTO.class);
    }
    
    // Create
    public AuthorDTO addAuthor(Author author) {
    	Author saved = this.repo.save(author);
        return this.mapAuthorToDTO(saved);
    }
	
	// Read
    public List<AuthorDTO> getAllAuthors() {
    	return this.repo.findAll().stream()
    			.map(this::mapAuthorToDTO).collect(Collectors.toList());
    }

	// Update
    public AuthorDTO updateAuthor(Long id, Author newAuthor) {
        Optional<Author> existingOptional = this.repo.findById(id);
        Author existing = existingOptional.get();

        existing.setFirstName(newAuthor.getFirstName());
        existing.setMiddleName(newAuthor.getMiddleName());
        existing.setLastName(newAuthor.getLastName());

        Author updated = this.repo.save(existing);
        return this.mapAuthorToDTO(updated);
    }

    // Delete
    public boolean removeAuthor(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}
