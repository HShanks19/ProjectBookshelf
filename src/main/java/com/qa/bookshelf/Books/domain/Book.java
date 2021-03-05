package com.qa.bookshelf.Books.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "Book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable =false)
	private Long id;
	
	@Column(name = "title", nullable =false)
	private String title;
	
	@Column(name = "author", nullable =false)
	private String author;
	
	@Column(name = "genre", nullable =false)
	private String genre;
	
	@Column(name = "year_released", nullable =false)
	private int yearReleased;

	@Column(name = "status", nullable =false)
	private Status status;
			
	public Book(String title, String author, String genre, int yearReleased, Status status) {
		super();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.yearReleased = yearReleased;
		this.status = status;
		
	}

	
	public Book() {
		super();
	}

	public Book(long id, String title, String author, String genre, int yearReleased, Status status) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.yearReleased = yearReleased;
		this.status = status;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId(String title) {
		return id;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(int yearReleased) {
		this.yearReleased = yearReleased;
	}
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + yearReleased;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (yearReleased != other.yearReleased)
			return false;
		return true;
	}
	

}
