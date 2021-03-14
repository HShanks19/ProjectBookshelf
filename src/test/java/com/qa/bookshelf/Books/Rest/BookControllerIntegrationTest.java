package com.qa.bookshelf.Books.Rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.bookshelf.Books.domain.Book;
import com.qa.bookshelf.Books.domain.Status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class BookControllerIntegrationTest {
	
	@Autowired 
	private MockMvc mockMVC;
	
	@Autowired
	private ObjectMapper mapper;
	// exact same object mapper as Spring. Advantage: Test gives same results.
	
	private long id = 1L;
	
	private final Book testBookFromDB = new Book(this.id,"Book of Books", "Mark", "Fantasy", 2020, Status.TOREAD);
	
	@Test
	void testCreate() throws Exception {
		Book newBook = new Book ("newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		
		String newBookAsJSON = this.mapper.writeValueAsString(newBook);
		
		RequestBuilder mockRequest = post("/createBook").contentType(MediaType.APPLICATION_JSON).content(newBookAsJSON);
		
		Book savedBook = new Book(4L, "newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		
		String savedBookAsJSON = this.mapper.writeValueAsString(savedBook);

		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedBookAsJSON);
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void readTest() throws Exception {

		List<Book> allBooks = List.of(testBookFromDB);
		
		String BooksAsJSON = this.mapper.writeValueAsString(allBooks);

		RequestBuilder mockRequest = get("/getBook");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(BooksAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void deleteTest() throws Exception {
		
		RequestBuilder mockRequest = delete("/removeBook/" + this.testBookFromDB.getId());
	
		ResultMatcher checkStatus = status().isNoContent();
	
		this.mockMVC.perform(mockRequest).andExpect(checkStatus);
	}
	
	@Test
	void updateTest() throws Exception {

		Book newBook = new Book("newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		
		String newBookAsJSON = this.mapper.writeValueAsString(newBook);
		
		RequestBuilder mockRequest = put("/updateBook/1").contentType(MediaType.APPLICATION_JSON)
				.content(newBookAsJSON);

		Book savedBook = new Book(1L, "newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		
		String savedBookAsJSON = this.mapper.writeValueAsString(savedBook);

		ResultMatcher matchStatus = status().isAccepted();
		
		ResultMatcher matchBody = content().json(savedBookAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
}
