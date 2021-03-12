package com.qa.bookshelf.Reviews.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bookshelf.Reviews.domain.Review;
import com.qa.bookshelf.Reviews.service.ReviewService;


@RestController
@CrossOrigin
public class ReviewController {

	private ReviewService service;

	public ReviewController(ReviewService service) {
		super();
		this.service = service;
	}
	
	
	//Working
	@PostMapping("/createReview")
	public ResponseEntity<Review> createReview(@RequestBody Review review) {
		return new ResponseEntity<Review>(this.service.createReview(review), HttpStatus.CREATED);
	}
		
	//Working
	@GetMapping("/getReview")
	public ResponseEntity<List<Review>> getReview() {
		return ResponseEntity.ok(this.service.getReview());
	}

	//Working
	@GetMapping("/getReview/{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable long id) {
		return ResponseEntity.ok(this.service.getReviewById(id));
	}

	//Working
	@DeleteMapping("/removeReview/{id}")
	public ResponseEntity<Review> removeReview(@PathVariable long id) {
		this.service.removeReview(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	//Working
	@PutMapping("/updateReview/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable long id, @RequestBody Review review) {
        return new ResponseEntity<>(this.service.updateReview(id, review),HttpStatus.ACCEPTED);
    }

}
