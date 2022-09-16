package com.qa.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.project.persistence.domain.Stock;
import com.qa.project.persistence.repository.StockRepository;

@SpringBootTest
public class StockServiceUnitTests {
	@Autowired
	private StockService service;
	@MockBean
	private StockRepository repo;
	
	// Skip mapStockToDTO() testing
	
	@Test
	void testAddStockRepo() {
		// GIVEN
	    final Stock STOCK = new Stock(null, 5);
	    final Stock SAVED_STOCK = new Stock(1L, 5);
		    
		// WHEN
	    Mockito.when(this.repo.save(STOCK)).thenReturn(SAVED_STOCK);

		// THEN
		   Assertions.assertThat(this.service.addStockRepo(STOCK)).isEqualTo(SAVED_STOCK);

		// Verify that our repo was accessed exactly once
	    Mockito.verify(this.repo, Mockito.times(1)).save(STOCK);
	}
	
	@Test
	void testGetAllStocksRepo() {
		// GIVEN
		final List<Stock> STOCKS = List.of(new Stock(1L, 5), new Stock(2L, 3));

		// WHEN
		Mockito.when(this.repo.findAll()).thenReturn(STOCKS);
		
		// THEN
		assertThat(this.service.getAllStocksRepo()).isEqualTo(STOCKS);

		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	void testUpdateStockRepo() {
		// GIVEN
		final Long ID = 1L;
		final Stock STOCK = new Stock(ID, 5);
		final Optional<Stock> OPTIONAL_STOCK = Optional.of(STOCK);
		final Stock UPDATED_STOCK = new Stock(ID, 4);
		
		// WHEN
		Mockito.when(this.repo.findById(ID)).thenReturn(OPTIONAL_STOCK);
		Mockito.when(this.repo.save(UPDATED_STOCK)).thenReturn(UPDATED_STOCK);
		
		// THEN
		assertThat(this.service.updateStockRepo(ID, UPDATED_STOCK)).isEqualTo(UPDATED_STOCK);
		
		// Verify that our repo was accessed twice
		Mockito.verify(this.repo, Mockito.times(1)).findById(ID);
		Mockito.verify(this.repo, Mockito.times(1)).save(UPDATED_STOCK);
	}
	
	@Test
	void testRemoveStock() {
		// GIVEN
		final Long ID = 1L;
		
		// WHEN
		// Don't need to mock this.repo.deleteById(ID), which returns void
		Mockito.when(this.repo.existsById(ID)).thenReturn(false);
		
		// THEN
		assertThat(this.service.removeStock(ID)).isEqualTo(true);
		
		// Verify that our repo was accessed exactly once
		Mockito.verify(this.repo, Mockito.times(1)).existsById(ID);
	}
}
