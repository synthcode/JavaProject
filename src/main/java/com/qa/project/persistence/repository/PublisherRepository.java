package com.qa.project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.project.persistence.domain.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	//
}