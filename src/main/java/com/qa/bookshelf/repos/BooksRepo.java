package com.qa.bookshelf.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.bookshelf.domain.Book;
import com.qa.bookshelf.domain.Status;

@Repository
public interface BooksRepo extends JpaRepository<Book,Long>{

	List<Book> findByAuthor(String author);
	
	List<Book> findByGenre (String genre);
	
	List<Book> findByYearReleased (int x);
	
	List<Book> findByStatus (Status status);
	
	Book findByTitle(String title);
	
}
