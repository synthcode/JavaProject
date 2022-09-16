package com.qa.project.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.project.persistence.domain.Book;
import com.qa.project.rest.dto.BookDTO;
import com.qa.project.service.BookService;

@WebMvcTest(BookController.class)
public class BookControllerWebIntegrationTests {

	@Autowired
	private BookController controller;

	@MockBean
	private BookService service;

	// test data
//	private List<Book> books = new ArrayList<>();
//	private Book testBook;
//	private Book bookUpdateInfo;
//	private Book updatedTestBook;
//	private Long updatedBookIsbn;
	
	final String TITLE_1 = "Title 1";
	final String TITLE_2 = "Title 2";
	final String TITLE_3 = "Title 3";
	final Long ISBN_1 = 9780123456700L;
	final Long ISBN_2 = 9781234567800L;
	final Long ISBN_3 = 9781234567900L;
	final LocalDate DATE_1 = LocalDate.of(1900, Month.JANUARY, 1);
	final LocalDate DATE_2 = LocalDate.of(2000, Month.JANUARY, 1);
	final LocalDate DATE_3 = LocalDate.of(2100, Month.JANUARY, 1);
	
	private List<Book> BOOKS = List.of(new Book(1L, TITLE_1, ISBN_1, DATE_1),
					 					new Book(2L, TITLE_2, ISBN_2, DATE_2));
	private List<BookDTO> BOOKS_DTO = List.of(new BookDTO(1L, TITLE_1, ISBN_1, DATE_1),
			                                   new BookDTO(2L, TITLE_2, ISBN_2, DATE_2));
	final Book BOOK_3 = new Book(null, TITLE_3, ISBN_3, DATE_3);
	
//	@Test
//	public void getBooksTest() {
//		// Given
//		ResponseEntity<List<BookDTO>> expected = new ResponseEntity<List<BookDTO>>(BOOKS_DTO, HttpStatus.OK);
//		// When
//		when(service.getAllBooksRepo()).thenReturn(BOOKS);
//		// Then
//		ResponseEntity<List<BookDTO>> actual = controller.getAllBooks();
//		assertThat(expected).isEqualTo(actual);
//		// verify service called
//		verify(service, times(1)).getAllBooksRepo();
//	}

}
