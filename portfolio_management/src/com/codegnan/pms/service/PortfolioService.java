package com.codegnan.pms.service;

import com.codegnan.pms.dao.PortfolioDAO;
import com.codegnan.pms.model.Portfolio;
import java.util.List;

/**
 * Service class for managing portfolios.
 */
public class PortfolioService {
    private final PortfolioDAO portfolioDAO;

    public PortfolioService() {
        this.portfolioDAO = new PortfolioDAO();
    }

    public void createPortfolio(int investorId, String portfolioName, String creationDate) {
        if (portfolioName == null || creationDate == null) {
            throw new IllegalArgumentException("Error: Portfolio name and creation date are required.");
        }
        Portfolio portfolio = new Portfolio(0, investorId, portfolioName, creationDate);
        portfolioDAO.createPortfolio(portfolio);
        System.out.println("Portfolio created successfully!");
    }

    public List<Portfolio> findByInvestorId(int investorId) {
        return portfolioDAO.findByInvestorId(investorId);
    }
    
    public Portfolio findByPortfolioId(int portFolioId) {
        return portfolioDAO.findByPortfolioId(portFolioId);
    }
    
    

    public void updatePortfolioName(int portfolioId, String newName) {
        portfolioDAO.updatePortfolioName(portfolioId, newName);
        System.out.println("Portfolio name updated successfully!");
    }

    public void deletePortfolio(int portfolioId) {
        portfolioDAO.deletePortfolio(portfolioId);
        System.out.println("Portfolio deleted successfully!");
    }
}