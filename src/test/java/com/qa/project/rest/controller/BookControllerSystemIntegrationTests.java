package com.qa.project.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//import java.awt.PageAttributes.MediaType;
import java.time.LocalDate;
import java.time.Month;
//import java.util.List;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.project.persistence.domain.Book;
import com.qa.project.persistence.repository.BookRepository;
//import com.qa.project.persistence.repository.BookRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class BookControllerSystemIntegrationTests {
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
	private BookRepository repo;
    
    // test data
 	// private List<Book> booksInDb = new ArrayList<>();
// 	private Book testBook;
// 	private Long testBookIsbn;
// 	private Book expectedTestBook;
// 	private Book bookUpdateInfo;

	// final String KEY_WORD = "Title";
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
	final Book BOOK_3 = new Book(null, TITLE_3, ISBN_3, DATE_3);
	private List<Book> booksInDatabase;
	
	@BeforeEach
	public void init() {
		//repo.saveAll(BOOKS);
		booksInDatabase.addAll(repo.saveAll(BOOKS));
//		List<Book> books = List.of(
//				new Book(9781492077992l, "Head First Design Patterns", "Freeman", "Eric", 2020, true, "O'Reilly", "UM"),
//				new Book(9780140237504l, "The Catcher in the Rye", "Salinger", "J.D.", 1946, false, "Penguin", "FB"));
//		booksInDb.addAll(repository.saveAll(books));
//		testBook = new Book(9780141182902l, "The Trail, English Translation", "Kafka", "Franz", 1994, false, "Penguin",
//				"FB");
//		testBookIsbn = testBook.getIsbn();
//		expectedTestBook = new Book(testBook.getIsbn(), testBook.getTitle(), testBook.getAuthorSurname(),
//				testBook.getAuthorForename(), testBook.getPubYear(), testBook.isDigital(), testBook.getPublisher(),
//				testBook.getGenreCode());
//		bookUpdateInfo = new Book(9781492077992l, "Head First Design Patterns, Second Edition", "Freeman", "Eric", 1993,
//				true, "O'Reilly", "UM");
	}

	@Test
	public void testGetBooks() throws Exception {
		// mock http request builder
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET, "/book/getAll");
		// specifying accept header return type
		mockRequest.accept(MediaType.APPLICATION_JSON);
		// JSON string for obj mapper
		String booksJson = mapper.writeValueAsString(BOOKS);
		// String booksJson = mapper.writeValueAsString(booksInDatabase);
		// result matcher
		ResultMatcher statusMatcher = MockMvcResultMatchers.status().isOk();
		ResultMatcher contentMatcher = MockMvcResultMatchers.content().json(booksJson);
		// request and assert
		mockMvc.perform(mockRequest).andExpect(statusMatcher).andExpect(contentMatcher);
	}
	
	@Test
	public void createBookTest() throws Exception {
		// test object
		final Book BOOK = BOOK_3;
		final Book EXPECTED_BOOK = new Book(3L, TITLE_3, ISBN_3, DATE_3);;
		
		// mock request
		MockHttpServletRequestBuilder mockRequest
		  = MockMvcRequestBuilders.request(HttpMethod.POST, "/book/create");
		
		// specifying accept header return type
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(mapper.writeValueAsString(BOOK));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		// ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher checkStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher checkContent
		  = MockMvcResultMatchers.content().json(mapper.writeValueAsString(EXPECTED_BOOK));
		
		mockMvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkContent);
	}
    
	/*
    @Test
    public void testCreateBook() throws Exception {
		String BOOK_AS_JSON = this.mapper.writeValueAsString(BOOK_3);

		final Book SAVED_BOOK = new Book(3L, TITLE_3, ISBN_3, DATE_3);
		String SAVED_BOOK_AS_JSON = this.mapper.writeValueAsString(SAVED_BOOK);

		// method, path, headers, body
		RequestBuilder request
		  = post("/book/create").contentType(MediaType.APPLICATION_JSON).content(BOOK_AS_JSON);

		ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher checkContent = MockMvcResultMatchers.content().json(SAVED_BOOK_AS_JSON);

		this.mockMvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
    }
    */
}
