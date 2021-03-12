package com.qa.bookshelf.Reviews.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.bookshelf.Reviews.domain.Review;
import com.qa.bookshelf.Reviews.repos.ReviewRepo;
import com.qa.bookshelf.Reviews.service.ReviewService;

@SpringBootTest
@ActiveProfiles("test")
public class ReviewServiceDBUnitTest {

	@Autowired
	private ReviewService service;
	
	@MockBean
	private ReviewRepo repo;
	
	@Test
	void testCreate() {
		//GIVEN
		Review newReview = new Review("Good", 5, "Good Book", 1);
		Review savedReview = new Review(1L,"Good", 5, "Good Book", 1);
		
		//WHEN
		Mockito.when(this.repo.save(newReview)).thenReturn(savedReview);
		
		//THEN
		assertThat(this.service.createReview(newReview)).isEqualTo(savedReview);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(newReview);
		
	}
	
	@Test
	void testUpdate() {
		long updateId = 1L;
				
		Review newReview = new Review("Good", 5, "Good Book", 1);
		
		Optional<Review> optionalReview = Optional.of(new Review(1L, "Bad", 1, "Bad Book", 1));
		
		Review updatedReview = new Review(updateId, newReview.getReviewTitle(), newReview.getRating(), newReview.getReviewBody(), newReview.getBookId());
	
		//WHEN
		Mockito.when(this.repo.findById(updateId)).thenReturn(optionalReview);
		Mockito.when(this.repo.save(updatedReview)).thenReturn(updatedReview);
		
		assertThat(this.service.updateReview(updateId, newReview)).isEqualTo(updatedReview);
	}
	
	@Test
	void testRead() {
		Review newReview = new Review(1L,"Good", 5, "Good Book", 1);
		
		List<Review> allReviews = new ArrayList<>();
		
		allReviews.add(newReview);
		
		Mockito.when(this.repo.findAll()).thenReturn(allReviews);
		
		assertThat(this.service.getReview()).isEqualTo(allReviews);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testDelete() {
		
		long id = 1L;
		
		Review newReview = new Review(id,"Good", 5, "Good Book", 1);
		Review savedReview = new Review(1L,"Good", 5, "Good Book", 1);
		
		
		//WHEN
		Mockito.when(this.repo.save(newReview)).thenReturn(savedReview);
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
	
		assertThat(this.service.removeReview(id)).isFalse();
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		
	}
}
