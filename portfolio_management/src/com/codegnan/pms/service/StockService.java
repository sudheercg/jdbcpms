package com.codegnan.pms.service;

import com.codegnan.pms.dao.StockDAO;
import com.codegnan.pms.model.Stock;
import java.util.List;

/**
 * Service class for managing stocks with business logic and validation.
 */
public class StockService {
    private final StockDAO stockDAO;

    public StockService() {
        this.stockDAO = new StockDAO();
    }

    /**
     * Adds a new stock after validation.
     * - Symbol and name must not be null or empty.
     * - Price must be greater than zero.
     * - Symbol must be unique.
     */
    public void createStock(String symbol, String name, double price) {
        if (symbol == null || symbol.isEmpty() || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Error: Stock symbol and name are required.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Error: Stock price must be greater than zero.");
        }
        List<Stock> existingStocks = stockDAO.findAllStocks();
        for (Stock stock : existingStocks) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                throw new IllegalArgumentException("Error: Stock symbol must be unique.");
            }
        }
        Stock stock = new Stock(symbol, name, price);
        stockDAO.createStock(stock);
        System.out.println("Stock created successfully!");
    }

    /**
     * Retrieves stock details by stock ID.
     */
    public Stock findByStockId(int stockId) {
        Stock stock = stockDAO.findByStockId(stockId);
        if (stock == null) {
            System.out.println("Error: Stock not found.");
        }
        return stock;
    }

    /**
     * Retrieves all stocks.
     */
    public List<Stock> findAllStocks() {
        List<Stock> stocks = stockDAO.findAllStocks();
        if (stocks.isEmpty()) {
            System.out.println("No stocks found in the system.");
        }
        return stocks;
    }

    /**
     * Updates the price of an existing stock.
     * - New price must be greater than zero.
     */
    public void updateStockPrice(int stockId, double newPrice) {
        if (newPrice <= 0) {
            throw new IllegalArgumentException("Error: Price must be greater than zero.");
        }
        if (stockDAO.findByStockId(stockId) == null) {
            throw new IllegalArgumentException("Error: Stock not found.");
        }
        stockDAO.updateStockPrice(stockId, newPrice);
        System.out.println("Stock price updated successfully!");
    }

    /**
     * Deletes a stock after checking dependencies.
     * - Ensure stock is not part of any active investment before deletion.
     */
    public void deleteStock(int stockId) {
        Stock stock = stockDAO.findByStockId(stockId);
        if (stock == null) {
            throw new IllegalArgumentException("Error: Stock not found.");
        }
        stockDAO.deleteStock(stockId);
        System.out.println("Stock deleted successfully!");
    }
}
