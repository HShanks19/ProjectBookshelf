package com.qa.bookshelf.service;

import java.util.List;

import com.qa.bookshelf.domain.Book;


public class BookServiceList implements BookService{

	private List<Book> books; // <- dependency

	public BookServiceList(List<Book> books) {
		super();
		this.books = books;
	}

	@Override
	public Book createBook(Book book) {
		this.books.add(book);
		Book added = this.books.get(this.books.size() - 1);
		return added;
	}

	@Override
	public List<Book> getBook() {
		return this.books;
	}

	@Override
	public Book getBookById(int id) {
		return this.books.get(id);
	}

	@Override
	public Book removeBook(int id) {
		return this.books.remove(id);
	}
	
	@Override
	public Book updateBook(int id, Book book) {
        this.books.remove(id);
        this.books.add(id, book);
        return this.books.get(id);
    }

}
