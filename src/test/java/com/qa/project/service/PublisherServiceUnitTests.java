package com.qa.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.project.persistence.domain.Publisher;
import com.qa.project.persistence.repository.PublisherRepository;

@SpringBootTest
public class PublisherServiceUnitTests {
	@Autowired
	private PublisherService service;
	@MockBean
	private PublisherRepository repo;
	
	// Setup
	final String NAME_1 = "Books Ltd";
	final String NAME_2 = "Press Plc";
	
	// Skip mapPublisherToDTO() testing
	
	@Test
	void testAddPublisherRepo() {
		// GIVEN
	    final Publisher PUBLISHER = new Publisher(null, NAME_1);
	    final Publisher SAVED_PUBLISHER = new Publisher(1L, NAME_2);
	    
		// WHEN
	    Mockito.when(this.repo.save(PUBLISHER)).thenReturn(SAVED_PUBLISHER);

		// THEN
	    Assertions.assertThat(this.service.addPublisherRepo(PUBLISHER)).isEqualTo(SAVED_PUBLISHER);

	    // Verify that our repo was accessed exactly once
	    Mockito.verify(this.repo, Mockito.times(1)).save(PUBLISHER);
	}
	
	@Test
	void testGetPublisherByIdRepo() {
		// GIVEN
		final Long ID = 1L;
		final Publisher PUBLISHER = new Publisher(ID, NAME_1);
		final Optional<Publisher> OPTIONAL_PUBLISHER = Optional.of(PUBLISHER);
		
		// WHEN
		Mockito.when(this.repo.findById(ID)).thenReturn(OPTIONAL_PUBLISHER);
		
		// THEN
		assertThat(this.service.getPublisherByIdRepo(ID)).isEqualTo(PUBLISHER);
		
		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
	}
	
	@Test
	void testGetAllPublishersRepo() {
		// GIVEN
		final List<Publisher> PUBLISHERS = List.of(new Publisher(1L, NAME_1),
				                                    new Publisher(2L, NAME_2));

		// WHEN
		Mockito.when(this.repo.findAll()).thenReturn(PUBLISHERS);
		
		// THEN
		assertThat(this.service.getAllPublishersRepo()).isEqualTo(PUBLISHERS);

		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testUpdatePublisherRepo() {
		// GIVEN
		final Long ID = 1L;
		final Publisher PUBLISHER = new Publisher(ID, NAME_1);
		final Optional<Publisher> OPTIONAL_PUBLISHER = Optional.of(PUBLISHER);
		final Publisher UPDATED_PUBLISHER = new Publisher(ID, NAME_2);
		
		// WHEN
		Mockito.when(this.repo.findById(ID)).thenReturn(OPTIONAL_PUBLISHER);
		Mockito.when(this.repo.save(UPDATED_PUBLISHER)).thenReturn(UPDATED_PUBLISHER);
		
		// THEN
		assertThat(this.service.updatePublisherRepo(ID, UPDATED_PUBLISHER)).isEqualTo(UPDATED_PUBLISHER);
		
		// Verify that our repo was accessed twice
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
		Mockito.verify(this.repo, Mockito.times(1)).save(UPDATED_PUBLISHER);
	}
	
	@Test
	void testRemovePublisher() {
		// GIVEN
		final Long ID = 1L;
		
		// WHEN
		// Don't need to mock this.repo.deleteById(ID), which returns void
		Mockito.when(this.repo.existsById(ID)).thenReturn(false);
		
		// THEN
		assertThat(this.service.removePublisher(ID)).isEqualTo(true);
		
		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).existsById(ID);
	}
}