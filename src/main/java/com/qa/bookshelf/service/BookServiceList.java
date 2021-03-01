package com.qa.bookshelf.service;

import java.util.ArrayList;
import java.util.List;

import com.qa.bookshelf.domain.Book;
import com.qa.bookshelf.domain.Status;

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
	public Book getBookById(long input_id) {
		for (int i = 0; i < books.size(); i++) {
			Book currentBook = books.get(i);
			if (currentBook.getId() == input_id) {
				return currentBook;
			}
		}
		return null;	

	}

	@Override
	public boolean removeBook(long input_id) {
		for (int i = 0; i < books.size(); i++) {
			Book currentBook = books.get(i);
			if (currentBook.getId() == input_id) {
				books.remove(currentBook);
			}
		}
		return false;
	}
	
	@Override
	public Book updateBook(long input_id, Book book) {
		for (int i = 0; i < books.size(); i++) {
			Book currentBook = books.get(i);
			if (currentBook.getId() == input_id) {
				currentBook.setTitle(book.getTitle());
				currentBook.setAuthor(book.getAuthor());
				currentBook.setGenre(book.getGenre());
				currentBook.setYearReleased(book.getYearReleased());
				
				return currentBook;
			}
		}
		return null;	
	}

	@Override
	public List<Book> getBooksByGenre(String genre) {
		for (int i = 0; i < books.size(); i++) {
			List<Book> books = new ArrayList<>();
			Book currentBook = books.get(i);
			if (currentBook.getGenre() == genre) {
				books.add(currentBook);
				return books;
			}
		}
		return null;
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		for (int i = 0; i < books.size(); i++) {
			List<Book> books = new ArrayList<>();
			Book currentBook = books.get(i);
			if (currentBook.getAuthor() == author) {
				books.add(currentBook);
				return books;
			}
		}
		return null;
	}

	@Override
	public List<Book> getBooksByYearReleased(int x) {
		for (int i = 0; i < books.size(); i++) {
			List<Book> books = new ArrayList<>();
			Book currentBook = books.get(i);
			if (currentBook.getYearReleased() == x) {
				books.add(currentBook);
				return books;
			}
		}
		return null;
	}



	@Override
	public Book getBookByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book startReadingBook(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book finishReadingBook(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByStatus(Status status) {
		// TODO Auto-generated method stub
		return null;
	}
}
