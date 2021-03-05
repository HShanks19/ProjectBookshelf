package com.qa.bookshelf.Reviews.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.bookshelf.Reviews.domain.Review;
import com.qa.bookshelf.Reviews.repos.ReviewRepo;

@Service
public class ReviewServiceDB implements ReviewService{

	private ReviewRepo repo;
	
    public ReviewServiceDB(ReviewRepo repo) {
        super();
        this.repo = repo;
    }
    
	@Override
	public Review createReview(Review review) {
		return this.repo.save(review);
	}
	
	@Override
	public List<Review> getReview() {
		return this.repo.findAll();
	}

	@Override
	public Review getReviewById(long id) {
		Optional<Review> existingOptional = this.repo.findById(id);
		Review existing = existingOptional.get();
		return existing;
	}


	@Override
	public boolean removeReview(long id) {
		this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
	}

	@Override
	public Review updateReview(long id, Review review) {
		Review existing = this.getReviewById(id);
		existing.setReviewTitle(review.getReviewTitle());
		existing.setRating(review.getRating());
		existing.setReviewBody(review.getReviewBody());
		return this.repo.save(existing);
	}
	

}
