package com.qa.project.persistence.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	@Modifying
	@Query(value = "INSERT INTO author_book (author_id, book_id) VALUES (:authorId, :id)",
	         nativeQuery = true)
	@Transactional
	void customAddAuthorToBook(Long id, Long authorId);
	
	@Modifying
	@Query(value = "DELETE FROM author_book WHERE author_id = :authorId AND book_id = :id",
	         nativeQuery = true)
	@Transactional
	void customDeleteAuthorFromBook(Long id, Long authorId);
}
