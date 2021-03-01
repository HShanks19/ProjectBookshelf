package com.qa.bookshelf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.bookshelf.domain.Book;
import com.qa.bookshelf.domain.Status;
import com.qa.bookshelf.repos.BooksRepo;

@Service
public class BookServiceDB implements BookService{

	private BooksRepo repo;

    public BookServiceDB(BooksRepo repo) {
        super();
        this.repo = repo;
    }
	
	@Override
	public Book createBook(Book book) {
		return this.repo.save(book);
	}

	@Override
	public List<Book> getBook() {
		return this.repo.findAll();
	}

	@Override
	public Book getBookById(long id) {
		Optional<Book> existingOptional = this.repo.findById(id);
		Book existing = existingOptional.get();
		return existing;
	}

	@Override
	public boolean removeBook(long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }


	@Override
	public Book updateBook(long id, Book newBook) {
		Book existing = this.getBookById(id);

		existing.setTitle(newBook.getTitle());
		existing.setAuthor(newBook.getAuthor());
		existing.setGenre(newBook.getGenre());
		existing.setYearReleased(newBook.getYearReleased());
		existing.setStatus(newBook.getStatus());

		return this.repo.save(existing);
	}

	@Override
	public List<Book> getBooksByGenre(String genre) {
		return this.repo.findByGenre(genre);
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		return this.repo.findByAuthor(author);
	}

	@Override
	public List<Book> getBooksByYearReleased(int x) {
		return this.repo.findByYearReleased(x);
	}
	
	@Override
	public Book startReadingBook(long id) {
		Book selectedBook = this.getBookById(id);
		
		selectedBook.setStatus(Status.READING);
		
		return this.repo.save(selectedBook);
		
	}

	@Override
	public Book finishReadingBook(long id) {
		Book selectedBook = this.getBookById(id);
		
		selectedBook.setStatus(Status.READ);
		
		return this.repo.save(selectedBook);
		
	}

	@Override
	public Book getBookByTitle(String title) {
		return this.repo.findByTitle(title);
	}

	@Override
	public List<Book> getBooksByStatus(Status status) {
		return this.repo.findByStatus(status);
	}

}
