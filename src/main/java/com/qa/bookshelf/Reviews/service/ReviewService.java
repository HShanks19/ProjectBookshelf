package com.qa.bookshelf.Reviews.service;

import java.util.List;

import com.qa.bookshelf.Reviews.domain.Review;

public interface ReviewService {

	Review createReview(Review review);

	Review getReviewById(long id);

	boolean removeReview(long id);
	
	Review updateReview(long id, Review review);

	List<Review> getReview();

//	Review createReviewFromInputId(long id, Review review);

}
