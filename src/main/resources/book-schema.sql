CREATE SCHEMA booktest;

CREATE TABLE booktest.book (
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
  	title VARCHAR(100) NOT NULL,
  	ISBN BIGINT(13) UNIQUE,
  	publication_date DATE,
  	publisher_id BIGINT(20),
	PRIMARY KEY (id)
);