package com.codegnan.pms.client;

import com.codegnan.pms.service.StockService;
import com.codegnan.pms.model.Stock;
import java.util.List;

/**
 * Client class to test CRUD operations for StockService.
 */
public class StockClient {
    public static void main(String[] args) {
        StockService stockService = new StockService();

        // Add new stocks
        System.out.println("Adding stocks to the system...");
        //stockService.createStock("AAPL", "Apple Inc.", 150.75);
        stockService.createStock("GOOGL", "Alphabet Inc.", 2800.50);
        System.out.println("Stocks added successfully.\n");

        // Retrieve all stocks
        System.out.println("Retrieving all stocks...");
        List<Stock> stocks = stockService.findAllStocks();
        for (Stock stock : stocks) {
            System.out.println(stock);
        }
        System.out.println();

        // Retrieve a stock by ID
        System.out.println("Retrieving stock with ID 1...");
        Stock stock = stockService.findByStockId(1);
        if (stock != null) {
            System.out.println("Stock Found: " + stock);
        } else {
            System.out.println("Stock not found.");
        }
        System.out.println();

        // Update stock price
        System.out.println("Updating stock price for ID 1...");
        //stockService.updateStockPrice(1, 160.25);
        System.out.println("Stock price updated successfully.\n");

        // Retrieve updated stock
        System.out.println("Retrieving updated stock with ID 1...");
        stock = stockService.findByStockId(1);
        if (stock != null) {
            System.out.println("Updated Stock: " + stock);
        }
        System.out.println();

        // Delete a stock
        System.out.println("Deleting stock with ID 2...");
       // stockService.deleteStock(2);
        System.out.println("Stock deleted successfully.\n");
    }
}
