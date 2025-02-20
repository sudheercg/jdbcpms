package com.codegnan.pms.client;

import com.codegnan.pms.service.InvestorService;
import com.codegnan.pms.service.PortfolioService;

/**
 * Client class for executing the application.
 */
public class ClientApplication {
    public static void main(String[] args) {
        InvestorService investorService = new InvestorService();
        investorService.createInvestor("John Doe", "john@example.com", "1234567890", "1990-01-01");
        
        PortfolioService portfolioService = new PortfolioService();
        portfolioService.createPortfolio(1, "Retirement Portfolio", "2025-02-20");
        
        
        
        
        
        
        
        
        
        
    }
}