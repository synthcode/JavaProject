package com.qa.project.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.qa.project.persistence.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	// Method findByLastName created by the JPA 
	public List<Author> findByLastName(String lastName);
	
	@Query(value = "SELECT * FROM author WHERE book_id = ?", nativeQuery = true)
	public List<Author> customFindAuthorsOfBook(Long bookId);
}
