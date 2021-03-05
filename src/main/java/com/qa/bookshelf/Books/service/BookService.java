package com.qa.bookshelf.Books.service;

import java.util.List;

import com.qa.bookshelf.Books.domain.Book;
import com.qa.bookshelf.Books.domain.Status;

public interface BookService {

	Book createBook(Book book);

	List<Book> getBook();

	Book getBookById(long id);

	boolean removeBook(long id);
	
	Book updateBook(long id, Book book);
	
	Book getBookByTitle(String title);
	
	List<Book> getBooksByGenre(String genre);
	
	List<Book> getBooksByAuthor(String author);
	
	List<Book> getBooksByYearReleased (int x);
	
	List<Book> getBooksByStatus (Status status);
	
	Book startReadingBook(long id);
	
	Book finishReadingBook(long id);

}
