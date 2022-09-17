package com.qa.project.rest.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.project.persistence.domain.Stock;
import com.qa.project.rest.dto.StockDTO;
import com.qa.project.service.StockService;

@RestController
public class StockController {
    private StockService service;

    public StockController(StockService service) {
        this.service = service;
    }
	
    // Test
	@GetMapping("/stock/test")
    public String test() {
        return "Hello, World! from Stock Controller";
    }
	
	// Create
    @PostMapping("/stock/create")
    public StockDTO addStock(@RequestBody Stock stock) {
        return this.service.addStock(stock);
    }
    
	// Read all
    @GetMapping("/stock/getAll")
    public List<StockDTO> getAllStocks() {
        return this.service.getAllStocks();
    }
    
    // Update
    @PutMapping("/stock/update")
    public StockDTO updateStock(@PathParam("bookId") Long id, @RequestBody Stock stock) {
        return this.service.updateStock(id, stock);
    }
    
    // Delete
    @DeleteMapping("/stock/delete/{id}")
    public boolean removeStock(@PathVariable Long id) {
        return this.service.removeStock(id);
    }
}
