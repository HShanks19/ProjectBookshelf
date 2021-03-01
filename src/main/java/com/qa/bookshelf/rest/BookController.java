package com.qa.bookshelf.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.bookshelf.domain.Book;
import com.qa.bookshelf.service.BookService;

@RestController
public class BookController {

	private BookService service;

	public BookController(BookService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createBook")
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		return new ResponseEntity<Book>(this.service.createBook(book), HttpStatus.CREATED);
	}

	@GetMapping("/getBook")
	public ResponseEntity<List<Book>> getBook() {
		return ResponseEntity.ok(this.service.getBook());
	}

	@GetMapping("/getBook/{id}")
	public Book getBookById(@PathVariable long id) {
		return this.service.getBookById(id);
	}

	@DeleteMapping("/removeChicken/{id}")
	public boolean removeBook(@PathVariable long id) {
		return this.service.removeBook(id);
	}
	
	@PutMapping("/updateBook/{id}")
    public Book updateBook(@PathVariable long id, @RequestBody Book book) {
        return this.service.updateBook(id, book);
    }

}
