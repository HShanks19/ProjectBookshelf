package com.qa.bookshelf.Reviews.service;

import java.util.List;

import com.qa.bookshelf.Reviews.domain.Review;

public interface ReviewService {

	Review createReview(Review review);

	List<Review> getReview();

	Review getReviewById(long id);

	boolean removeReview(long id);
	
	Review updateReview(long id, Review review);

//	Review createReviewFromInputId(long id, Review review);

}
