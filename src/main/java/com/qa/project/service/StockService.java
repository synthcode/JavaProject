package com.qa.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

// import com.qa.project.exception.StockNotFoundException;
import com.qa.project.persistence.domain.Stock;
import com.qa.project.persistence.repository.StockRepository;
import com.qa.project.rest.dto.StockDTO;

@Service
public class StockService {
	private StockRepository repo;
	private ModelMapper mapper;

    public StockService(StockRepository repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }
    
    // private
    private StockDTO mapStockToDTO(Stock stock) {
        return this.mapper.map(stock, StockDTO.class);
    }
    
    // Create
    protected Stock addStockRepo(Stock stock) {
    	return this.repo.save(stock);
    }
    public StockDTO addStock(Stock stock) {
    	return this.mapStockToDTO(addStockRepo(stock));
    }
    
	// Read all
    public List<Stock> getAllStocksRepo() {
    	return this.repo.findAll();
    }
    public List<StockDTO> getAllStocks() {
    	return getAllStocksRepo().stream().map(this::mapStockToDTO).collect(Collectors.toList());
    }
   
	// Update
    protected Stock updateStockRepo(Long id, Stock updatedStock) {
    	 Optional<Stock> existingOptional = this.repo.findById(id);
         Stock existing = existingOptional.get();

         existing.setQuantity(updatedStock.getQuantity());

         return this.repo.save(existing);
    }
    public StockDTO updateStock(Long id, Stock updatedStock) {
    	return this.mapStockToDTO(updateStockRepo(id, updatedStock));
    }

    // Delete
    public boolean removeStock(Long id) {
        this.repo.deleteById(id);
        boolean exists = this.repo.existsById(id);
        return !exists;
    }
}
