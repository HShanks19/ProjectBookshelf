package com.qa.bookshelf.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.bookshelf.domain.Book;

@Repository
public interface BooksRepo extends JpaRepository<Book,Long>{

}
