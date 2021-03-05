package com.qa.bookshelf.Reviews.Rest;

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
import com.qa.bookshelf.Reviews.domain.Review;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class ReviewControllerIntegrationTest {

	@Autowired 
	private MockMvc mockMVC;
	
	@Autowired
	private ObjectMapper mapper;

	private long id = 1L;
	
	private final Review testReviewFromDB = new Review(this.id,"Good book", 1 , "I loved this book it was amazing", 1);
	
	@Test
	void testCreate() throws Exception {
		
		Review newReview = new Review ("newTitle", 3, "newBody", 1);
		
		String newReviewAsJSON = this.mapper.writeValueAsString(newReview);
		
		RequestBuilder mockRequest = post("/createReview").contentType(MediaType.APPLICATION_JSON).content(newReviewAsJSON);
		
		Review savedReview = new Review(4L, "newTitle", 3, "newBody", 1);
		
		String savedReviewAsJSON = this.mapper.writeValueAsString(savedReview);

		ResultMatcher matchStatus = status().isCreated();
		ResultMatcher matchBody = content().json(savedReviewAsJSON);
		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}
	
	@Test
	void readTest() throws Exception {

		List<Review> allReviews = List.of(testReviewFromDB);
		
		String ReviewsAsJSON = this.mapper.writeValueAsString(allReviews);

		RequestBuilder mockRequest = get("/getReview");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(ReviewsAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void deleteTest() throws Exception {
		
		RequestBuilder mockRequest = delete("/removeReview/" + this.testReviewFromDB.getId());
	
		ResultMatcher checkStatus = status().isNoContent();
	
		this.mockMVC.perform(mockRequest).andExpect(checkStatus);
	}
	
	@Test
	void updateTest() throws Exception {

		Review newReview = new Review ("newTitle", 3, "newBody", 1);
		
		String newReviewAsJSON = this.mapper.writeValueAsString(newReview);
		
		RequestBuilder mockRequest = put("/updateReview/1").contentType(MediaType.APPLICATION_JSON)
				.content(newReviewAsJSON);

		Review savedReview = new Review(1L, "newTitle", 3, "newBody", 1);
		
		String savedReviewAsJSON = this.mapper.writeValueAsString(savedReview);

		ResultMatcher matchStatus = status().isAccepted();
		
		ResultMatcher matchBody = content().json(savedReviewAsJSON);

		this.mockMVC.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);
	}

	
}