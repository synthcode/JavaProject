package com.qa.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.project.persistence.domain.Book;
import com.qa.project.persistence.repository.BookRepository;

@SpringBootTest
public class BookServiceUnitTest {
	@Autowired
	private BookService service;
	@MockBean
	private BookRepository repo;
	
	// @Before
	final String KEY_WORD = "Title";
	final String TITLE_1 = "Title 1";
	final String TITLE_2 = "Title 2";
	final Long ISBN_1 = 9780123456789L;
	final Long ISBN_2 = 9781234567890L;
	final LocalDate DATE_1 = LocalDate.of(1900, Month.JANUARY, 1);
	final LocalDate DATE_2 = LocalDate.of(2000, Month.JANUARY, 1);

	// Skip mapBookToDTO() testing
	
	@Test
	void testAddBookRepo() {
		// GIVEN
	    final Book BOOK = new Book(null, TITLE_1, ISBN_1, DATE_1);
	    final Book SAVED_BOOK = new Book(1L, TITLE_1, ISBN_1, DATE_1);
	    
		// WHEN
	    Mockito.when(this.repo.save(BOOK)).thenReturn(SAVED_BOOK);

		// THEN
	    Assertions.assertThat(this.service.addBookRepo(BOOK)).isEqualTo(SAVED_BOOK);

	    // Verify that our repo was accessed exactly once
	    Mockito.verify(this.repo, Mockito.times(1)).save(BOOK);
	}
	
	@Test
	void testGetBookByIdRepo() {
		// GIVEN
		final Long ID = 1L;
		final Book BOOK = new Book(ID, TITLE_1, ISBN_1, DATE_1);
		final Optional<Book> OPTIONAL_BOOK = Optional.of(BOOK);
		
		// WHEN
		Mockito.when(this.repo.findById(ID)).thenReturn(OPTIONAL_BOOK);
		
		// THEN
		assertThat(this.service.getBookByIdRepo(ID)).isEqualTo(BOOK);
		
		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
	}
	
	@Test
	void testGetBookByTitleKeyWordRepo() {
		// GIVEN
		final List<Book> BOOKS = List.of(new Book(1L, TITLE_1, ISBN_1, DATE_1),
                                          new Book(2L, TITLE_2, ISBN_2, DATE_2));
		
		// WHEN
		Mockito.when(this.repo.findByTitleContaining(KEY_WORD)).thenReturn(BOOKS);
		
		// THEN
		assertThat(this.service.getBookByTitleKeyWordRepo(KEY_WORD)).isEqualTo(BOOKS);
		
		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).findByTitleContaining(KEY_WORD);
	}

	@Test
	void testGetAllBooksRepo() {
		// GIVEN
		final List<Book> BOOKS = List.of(new Book(1L, TITLE_1, ISBN_1, DATE_1),
				                          new Book(2L, TITLE_2, ISBN_2, DATE_2));

		// WHEN
		Mockito.when(this.repo.findAll()).thenReturn(BOOKS);
		
		// THEN
		assertThat(this.service.getAllBooksRepo()).isEqualTo(BOOKS);

		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testUpdateBookRepo() {
		// GIVEN
		final Long ID = 1L;
		final Book BOOK = new Book(ID, TITLE_1, ISBN_1, DATE_1);
		final Optional<Book> OPTIONAL_BOOK = Optional.of(BOOK);
		final Book UPDATED_BOOK = new Book(ID, TITLE_2, ISBN_2, DATE_2);
		
		// WHEN
		Mockito.when(this.repo.findById(ID)).thenReturn(OPTIONAL_BOOK);
		Mockito.when(this.repo.save(UPDATED_BOOK)).thenReturn(UPDATED_BOOK);
		
		// THEN
		assertThat(this.service.updateBookRepo(ID, UPDATED_BOOK)).isEqualTo(UPDATED_BOOK);
		
		// Verify that our repo was accessed twice
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
		Mockito.verify(this.repo, Mockito.times(1)).save(UPDATED_BOOK);
	}
	
	@Test
	void testRemoveBook() {
		// GIVEN
		final Long ID = 1L;
		
		// WHEN
		// Don't need to mock this.repo.deleteById(ID), which returns void
		Mockito.when(this.repo.existsById(ID)).thenReturn(false);
		
		// THEN
		assertThat(this.service.removeBook(ID)).isEqualTo(true);
		
		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).existsById(ID);
	}
}