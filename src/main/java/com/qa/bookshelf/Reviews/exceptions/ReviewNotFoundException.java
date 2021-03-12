package com.qa.bookshelf.Reviews.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The Review does not exist")
public class ReviewNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7326620371174226014L;

}
