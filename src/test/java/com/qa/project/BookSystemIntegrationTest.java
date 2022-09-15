package com.qa.project;

import java.time.LocalDate;
import java.time.Month;
//import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.project.persistence.domain.Book;
//import com.qa.project.persistence.repository.BookRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class BookSystemIntegrationTest {
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;
    
//	@Autowired
//	private BookRepository repo;
    
	final String KEY_WORD = "Title";
	final String TITLE_1 = "Title 1";
	final String TITLE_2 = "Title 2";
	final Long ISBN_1 = 9780123456700L;
	final Long ISBN_2 = 9781234567800L;
	final LocalDate DATE_1 = LocalDate.of(1900, Month.JANUARY, 1);
	final LocalDate DATE_2 = LocalDate.of(2000, Month.JANUARY, 1);
	
//	private List<Book> booksDatabase = List.of(new Book(1L, TITLE_1, ISBN_1, DATE_1),
//                                                new Book(2L, TITLE_2, ISBN_2, DATE_2));
	
	@Test
	public void createBookTest() throws Exception {
		// test object
		final Book BOOK = new Book(1L, TITLE_1, ISBN_1, DATE_1);
		final Book EXPECTED_BOOK = new Book(1L, TITLE_1, ISBN_1, DATE_1);
		
		// mock request
		MockHttpServletRequestBuilder mockRequest
		  = MockMvcRequestBuilders.request(HttpMethod.POST, "/book/create");
		
		// specifying accept header return type
		mockRequest.contentType(MediaType.APPLICATION_JSON);
		mockRequest.content(mapper.writeValueAsString(BOOK));
		mockRequest.accept(MediaType.APPLICATION_JSON);
		
		ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher checkContent
		  = MockMvcResultMatchers.content().json(mapper.writeValueAsString(EXPECTED_BOOK));
		
		mockMvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkContent);
	}
    
//    @Test
//    public void testCreateBook() throws Exception {
//    	final Book BOOK = new Book(null, TITLE_1, ISBN_1, DATE_1);
//		String BOOK_AS_JSON = this.mapper.writeValueAsString(BOOK);
//
//		final Book SAVED_BOOK = new Book(1L, TITLE_1, ISBN_1, DATE_1);
//		String SAVED_BOOK_AS_JSON = this.mapper.writeValueAsString(SAVED_BOOK);
//
//		// method, path, headers, body
//		RequestBuilder request
//		  = post("/book/create").contentType(MediaType.APPLICATION_JSON).content(BOOK_AS_JSON);
//
//		ResultMatcher checkStatus = MockMvcResultMatchers.status().isCreated();
//		ResultMatcher checkContent = MockMvcResultMatchers.content().json(SAVED_BOOK_AS_JSON);
//
//		this.mockMvc.perform(request).andExpect(checkStatus).andExpect(checkContent);
//    	
//    	/*
//    	MockHttpServletRequestBuilder mockRequest
//    	  = MockMvcRequestBuilders.request(HttpMethod.POST, "/book/createBook");
//    	mockRequest.contentType(MediaType.APPLICATION_JSON);
//    	mockRequest.content(this.jsonifier.writeValueAsString(testBook));
//    	mockRequest.accept(MediaType.APPLICATION_JSON);
//    	ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
//    	ResultMatcher matchContent
//    	  = MockMvcResultMatchers.content().json(this.jsonifier.writeValueAsString(bookDTO));
//    	this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
//    	*/
//    }
}
