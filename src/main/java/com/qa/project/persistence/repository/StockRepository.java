package com.qa.project.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.project.persistence.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
	//
}
