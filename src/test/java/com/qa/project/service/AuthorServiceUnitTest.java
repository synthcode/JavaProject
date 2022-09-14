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

import com.qa.project.persistence.domain.Author;
import com.qa.project.persistence.domain.Book;
import com.qa.project.persistence.repository.AuthorRepository;

@SpringBootTest
public class AuthorServiceUnitTest {
	@Autowired
	private AuthorService service;
	@MockBean
	private AuthorRepository repo;
	
	// @Before
	final Long BOOKID_1 = null;
	final Long BOOKID_2 = null;
	final String FIRST_1 = "Joe";
	final String FIRST_2 = "John";
	final String MIDDLE_1 = null;
	final String MIDDLE_2 = null;
	final String LAST_1 = "Bloggs";
	final String LAST_2 = "Smith";

	// Skip mapAuthorToDTO() testing
	
	@Test
	void testAddAuthorRepo() {
		// GIVEN
	    final Author AUTHOR = new Author(null, BOOKID_1, FIRST_1, MIDDLE_1, LAST_1);
	    final Author SAVED_AUTHOR = new Author(1L, BOOKID_1, FIRST_1, MIDDLE_1, LAST_1);
	    
		// WHEN
	    Mockito.when(this.repo.save(AUTHOR)).thenReturn(SAVED_AUTHOR);

		// THEN
	    Assertions.assertThat(this.service.addAuthorRepo(AUTHOR)).isEqualTo(SAVED_AUTHOR);

	    // Verify that our repo was accessed exactly once
	    Mockito.verify(this.repo, Mockito.times(1)).save(AUTHOR);
	}
	
	@Test
	void testGetAuthorByIdRepo() {
		// GIVEN
		final Long ID = 1L;
		final Author AUTHOR = new Author(ID, BOOKID_1, FIRST_1, MIDDLE_1, LAST_1);
		final Optional<Author> OPTIONAL_AUTHOR = Optional.of(AUTHOR);
		
		// WHEN
		Mockito.when(this.repo.findById(ID)).thenReturn(OPTIONAL_AUTHOR);
		
		// THEN
		assertThat(this.service.getAuthorByIdRepo(ID)).isEqualTo(AUTHOR);
		
		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
	}
	
	@Test
	void testGetAllAuthorsRepo() {
		// GIVEN
		final List<Author> AUTHORS = List.of(new Author(1L, BOOKID_1, FIRST_1, MIDDLE_1, LAST_1),
				                              new Author(2L, BOOKID_2, FIRST_2, MIDDLE_2, LAST_2));

		// WHEN
		Mockito.when(this.repo.findAll()).thenReturn(AUTHORS);
		
		// THEN
		assertThat(this.service.getAllAuthorsRepo()).isEqualTo(AUTHORS);

		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testUpdateAuthorRepo() {
		// GIVEN
		final Long ID = 1L;
		final Author AUTHOR = new Author(ID, BOOKID_1, FIRST_1, MIDDLE_1, LAST_1);
		final Optional<Author> OPTIONAL_AUTHOR = Optional.of(AUTHOR);
		final Author UPDATED_AUTHOR = new Author(ID, BOOKID_2, FIRST_2, MIDDLE_2, LAST_2);
		
		// WHEN
		Mockito.when(this.repo.findById(ID)).thenReturn(OPTIONAL_AUTHOR);
		Mockito.when(this.repo.save(UPDATED_AUTHOR)).thenReturn(UPDATED_AUTHOR);
		
		// THEN
		assertThat(this.service.updateAuthorRepo(ID, UPDATED_AUTHOR)).isEqualTo(UPDATED_AUTHOR);
		
		// Verify that our repo was accessed twice
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
		Mockito.verify(this.repo, Mockito.times(1)).save(UPDATED_AUTHOR);
	}
	
	@Test
	void testRemoveAuthor() {
		// GIVEN
		final Long ID = 1L;
		
		// WHEN
		// Don't need to mock this.repo.deleteById(ID), which returns void
		Mockito.when(this.repo.existsById(ID)).thenReturn(false);
		
		// THEN
		assertThat(this.service.removeAuthor(ID)).isEqualTo(true);
		
		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).existsById(ID);
	}
}
