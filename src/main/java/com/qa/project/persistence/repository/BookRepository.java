package com.qa.project.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.project.persistence.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	// Method findByTitleContaining created by the JPA
	public List<Book> findByTitleContaining(String keyWord);
}
