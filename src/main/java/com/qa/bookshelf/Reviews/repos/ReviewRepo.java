package com.qa.bookshelf.Reviews.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.bookshelf.Reviews.domain.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long>{

}

	