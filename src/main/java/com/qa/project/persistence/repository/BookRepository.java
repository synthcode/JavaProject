package com.qa.project.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.project.persistence.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	// Method findByTitleContaining created by the JPA
	public List<Book> findByTitleContaining(String keyWord);
	
	@Query(value = "SELECT * FROM book WHERE publisher_id = ?", nativeQuery = true)
	public List<Book> customFindBooksOfPublisher(Long publisherId);
	
	@Query(value = "SELECT * FROM author_book INNER JOIN book"
		     + " ON author_book.book_id = book.id"
	           + " WHERE author_book.author_id = ?", nativeQuery = true)
	public List<Book> customFindBooksOfAuthor(Long authorId);
}
