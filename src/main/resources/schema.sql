CREATE SCHEMA IF NOT EXISTS book;
CREATE SCHEMA IF NOT EXISTS review;

CREATE TABLE IF NOT EXISTS book.book(
  id bigint NOT NULL AUTO_INCREMENT,
  author varchar(255) NOT NULL,
  genre varchar(255) NOT NULL,
  status int NOT NULL,
  title varchar(255) NOT NULL,
  year_released int NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS review.review(
  id bigint NOT NULL AUTO_INCREMENT,
  rating int NOT NULL,
  review_body varchar(255) NOT NULL,
  review_title varchar(255) NOT NULL,
  book_id bigint DEFAULT NULL,
  PRIMARY KEY (id)
 );
 
 
 

  