package com.qa.bookshelf.service;

import java.util.List;

import com.qa.bookshelf.domain.Book;

public interface BookService {

	Book createBook(Book book);

	List<Book> getBook();

	Book getBookById(long id);

	boolean removeBook(long id);
	
	Book updateBook(long id, Book book);

}
