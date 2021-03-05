package com.qa.bookshelf.Books.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.bookshelf.Books.domain.Book;
import com.qa.bookshelf.Books.domain.Status;
import com.qa.bookshelf.Books.repos.BooksRepo;
import com.qa.bookshelf.Books.service.BookServiceDB;

@SpringBootTest
@ActiveProfiles("test")
public class BookServiceDBUnitTest {

	@Autowired
	private BookServiceDB service;
	
	@MockBean
	private BooksRepo repo;
	
	@Test
	void testCreate() {
		//GIVEN
		Book newBook = new Book("newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		Book savedBook = new Book(1L,"newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		
		//WHEN
		Mockito.when(this.repo.save(newBook)).thenReturn(savedBook);
		
		//THEN
		assertThat(this.service.createBook(newBook)).isEqualTo(savedBook);
		
		Mockito.verify(this.repo, Mockito.times(1)).save(newBook);
		
	}
	@Test
	void testUpdate() {
		long updateId = 1L;
		
		Book newBook = new Book("newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		
		Optional<Book> optionalBook = Optional.of(new Book(updateId, "Book of Books", "Mark", "fantasy", 2020, Status.TOREAD));
		
		Book updatedBook = new Book(updateId, newBook.getTitle(), newBook.getAuthor(), newBook.getGenre(), newBook.getYearReleased(), newBook.getStatus());
	
		//WHEN
		Mockito.when(this.repo.findById(updateId)).thenReturn(optionalBook);
		Mockito.when(this.repo.save(updatedBook)).thenReturn(updatedBook);
		
		assertThat(this.service.updateBook(updateId, newBook)).isEqualTo(updatedBook);
	}
	@Test
	void testRead() {
		Book newBook = new Book(1L,"newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		
		List<Book> allBooks = new ArrayList<>();
		
		allBooks.add(newBook);
		
		Mockito.when(this.repo.findAll()).thenReturn(allBooks);
		
		assertThat(this.service.getBook()).isEqualTo(allBooks);
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testDelete() {
		
		long id = 1L;
		
		Book newBook = new Book(id,"newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		Book savedBook = new Book(1L,"newTitle", "newAuthor", "newGenre", 2010, Status.READ);
		
		//WHEN
		Mockito.when(this.repo.save(newBook)).thenReturn(savedBook);
		Mockito.when(this.repo.existsById(id)).thenReturn(true);
	
		assertThat(this.service.removeBook(id)).isFalse();
		
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(id);
		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		
	}

}
