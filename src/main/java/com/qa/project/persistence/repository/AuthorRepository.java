package com.qa.project.persistence.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.project.persistence.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	// Method findByLastName created by the JPA 
	public List<Author> findByLastName(String lastName);
		
	@Query(value = "SELECT * FROM author_book INNER JOIN author"
				     + " ON author_book.author_id = author.id"
			           + " WHERE author_book.book_id = ?", nativeQuery = true)
	public List<Author> customFindAuthorsOfBook(Long bookId);
	
	@Modifying
	@Query(value = "INSERT INTO author_book (author_id, book_id) VALUES (:id, :bookId)",
	         nativeQuery = true)
	@Transactional
	void customAddBookToAuthor(Long id, Long bookId);
	
	@Modifying
	@Query(value = "DELETE FROM author_book WHERE author_id = :id AND book_id = :bookId",
	         nativeQuery = true)
	@Transactional
	void customDeleteBookFromAuthor(Long id, Long bookId);
}
