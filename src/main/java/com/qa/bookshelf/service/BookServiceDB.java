package com.qa.bookshelf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.bookshelf.domain.Book;
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

		return this.repo.save(existing);
	}

}
