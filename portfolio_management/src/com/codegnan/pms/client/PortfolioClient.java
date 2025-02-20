package com.codegnan.pms.client;

import com.codegnan.pms.service.PortfolioService;
import com.codegnan.pms.model.Portfolio;
import java.util.List;

/**
 * Client class to test CRUD operations for PortfolioService.
 */
public class PortfolioClient {
    public static void main(String[] args) {
        PortfolioService portfolioService = new PortfolioService();

        // Create new portfolios
        System.out.println("Creating portfolios...");
        portfolioService.createPortfolio(1, "Retirement Fund", "2025-01-01");
        portfolioService.createPortfolio(1, "Growth Portfolio", "2025-02-01");
        System.out.println("Portfolios created successfully.\n");

        // Retrieve all portfolios for an investor
        System.out.println("Retrieving portfolios for investor ID 1...");
        List<Portfolio> portfolios = portfolioService.findByInvestorId(1);
        for (Portfolio portfolio : portfolios) {
            System.out.println(portfolio);
        }
        System.out.println();

        // Update portfolio name
        System.out.println("Updating portfolio with ID 1...");
        portfolioService.updatePortfolioName(1, "Updated Retirement Fund");
        System.out.println("Portfolio name updated successfully.\n");

        // Retrieve updated portfolio
        System.out.println("Retrieving updated portfolio with ID 1...");
        Portfolio updatedPortfolio = portfolioService.findByPortfolioId(1);
        if (updatedPortfolio != null) {
            System.out.println("Updated Portfolio: " + updatedPortfolio);
        }
        System.out.println();

        // Delete a portfolio
        System.out.println("Deleting portfolio with ID 2...");
      //  portfolioService.deletePortfolio(2);
        System.out.println("Portfolio deleted successfully.\n");
    }
}
