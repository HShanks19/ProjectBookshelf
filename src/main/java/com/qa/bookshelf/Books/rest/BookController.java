package com.qa.bookshelf.Books.rest;

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

import com.qa.bookshelf.Books.domain.Book;
import com.qa.bookshelf.Books.domain.Status;
import com.qa.bookshelf.Books.service.BookService;

@RestController
@CrossOrigin
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

	@GetMapping("/getBookID/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable long id) {
		return ResponseEntity.ok(this.service.getBookById(id));
	}
	
	//pointless get rid
	@GetMapping("/getBookTitle/{id}")
	public ResponseEntity<Book> getBookByTitle(@PathVariable long id) {
		return ResponseEntity.ok(this.service.getBookById(id));
	}

	@DeleteMapping("/removeBook/{id}")
	public ResponseEntity<Book> removeBook(@PathVariable long id) {
		this.service.removeBook(id);
		return new ResponseEntity<> (HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/updateBook/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book book) {
        return new ResponseEntity<>(this.service.updateBook(id, book), HttpStatus.ACCEPTED);
    }
	
	
	@PutMapping("/startBook/{id}")
    public Book startReadingBook(@PathVariable long id) {
        return this.service.startReadingBook(id);
    }
	
	@PutMapping("/finishBook/{id}")
    public Book finishReadingBook(@PathVariable long id) {
        return this.service.finishReadingBook(id);
    }
	
	@GetMapping("/getBookStatus/{status}")
	public List<Book> getBooksByStatus(@PathVariable String status) {
		if (status.contains("TOREAD")){
			return this.service.getBooksByStatus(Status.TOREAD);
		} else if  (status.contains("READING")){
			return this.service.getBooksByStatus(Status.READING);
		} else  {
			return this.service.getBooksByStatus(Status.READ);
		} 
	}
	
}
