package com.qa.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.project.exception.PublisherNotFoundException;
import com.qa.project.persistence.domain.Publisher;
import com.qa.project.persistence.repository.PublisherRepository;
import com.qa.project.rest.dto.PublisherDTO;

@Service
public class PublisherService {
	private PublisherRepository repo;
	private ModelMapper mapper;

    public PublisherService(PublisherRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    
    // private
    private PublisherDTO mapPublisherToDTO(Publisher publisher) {
        return this.mapper.map(publisher, PublisherDTO.class);
    }
    
    // Create
    protected Publisher addPublisherRepo(Publisher publisher) {
    	return this.repo.save(publisher);
    }
    public PublisherDTO addPublisher(Publisher publisher) {
    	return this.mapPublisherToDTO(addPublisherRepo(publisher));
    }
    
	// Read by id
    protected Publisher getPublisherByIdRepo(Long id) {
   	 	// Optional<Publisher> optionalPublisher = this.repo.findById(id);
   	 	// return optionalPublisher.get();
   	 	return this.repo.findById(id).orElseThrow(PublisherNotFoundException::new);
    }
    public PublisherDTO getPublisherById(Long id) {
    	return this.mapPublisherToDTO(getPublisherByIdRepo(id));
    }
    
	// Read all
    protected List<Publisher> getAllPublishersRepo() {
    	return this.repo.findAll();
    }
    public List<PublisherDTO> getAllPublishers() {
    	return getAllPublishersRepo().stream().map(this::mapPublisherToDTO).collect(Collectors.toList());
    }
   
	// Update
    protected Publisher updatePublisherRepo(Long id, Publisher updatedPublisher) {
    	 Optional<Publisher> existingOptional = this.repo.findById(id);
         Publisher existing = existingOptional.get();

         existing.setName(updatedPublisher.getName());

         return this.repo.save(existing);
    }
    public PublisherDTO updatePublisher(Long id, Publisher updatedPublisher) {
    	return this.mapPublisherToDTO(updatePublisherRepo(id, updatedPublisher));
    }

    // Delete
    public boolean removePublisher(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}
