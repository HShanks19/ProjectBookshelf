package com.qa.bookshelf.Reviews.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "Review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable =false)
	private Long id;
	
	@Column(name = "review_title")
	private String reviewTitle;
	
	@Column(name = "rating")
	private int rating;
	
	@Column(name = "review_body")
	private String reviewBody;
	
	@Column(name = "book_id")
	private long bookId;
		
	public Review() {
		super();
	}
	
	public Review(String reviewTitle, int rating, String reviewBody, long bookId) {
		this.reviewTitle = reviewTitle;
		this.rating = rating;
		this.reviewBody = reviewBody;
		this.bookId = bookId; 
	}
		
	public Review(long id, String reviewTitle, int rating, String reviewBody, long bookId) {
		super();
		this.id = id;
		this.reviewTitle = reviewTitle;
		this.rating = rating;
		this.reviewBody = reviewBody;
		this.bookId = bookId; 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	
	public String getReviewBody() {
		return reviewBody;
	}
	public void setReviewBody(String reviewBody) {
		this.reviewBody = reviewBody;
	}
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (bookId ^ (bookId >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + rating;
		result = prime * result + ((reviewBody == null) ? 0 : reviewBody.hashCode());
		result = prime * result + ((reviewTitle == null) ? 0 : reviewTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Review other = (Review) obj;
		if (bookId != other.bookId)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rating != other.rating)
			return false;
		if (reviewBody == null) {
			if (other.reviewBody != null)
				return false;
		} else if (!reviewBody.equals(other.reviewBody))
			return false;
		if (reviewTitle == null) {
			if (other.reviewTitle != null)
				return false;
		} else if (!reviewTitle.equals(other.reviewTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Review [id=" + id + ", reviewTitle=" + reviewTitle + ", rating=" + rating + ", reviewBody=" + reviewBody
				+ ", bookId=" + bookId + "]";
	}


	
}
