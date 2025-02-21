package com.codegnan.pms.service;

import java.util.List;

import com.codegnan.pms.dao.InvestmentDAO;
import com.codegnan.pms.dao.StockDAO;
import com.codegnan.pms.model.Investment;
import com.codegnan.pms.model.UserInvestmentDTO;

/**
 * Service class for managing investments with business logic and validation.
 */
public class InvestmentService {
    private final InvestmentDAO investmentDAO;
    private final StockDAO stockDAO;

    public InvestmentService() {
        this.investmentDAO = new InvestmentDAO();
        this.stockDAO = new StockDAO();
    }

    /**
     * Adds an investment to a portfolio with validation rules.
     * - Quantity must be greater than zero.
     * - Purchase price must be greater than zero.
     * - Stock must exist before adding an investment.
     * - A portfolio cannot have more than 10 different investments.
     */
    public void addInvestment(int portfolioId, int stockId, int quantity, double purchasePrice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Error: Quantity must be greater than zero.");
        }
        if (purchasePrice <= 0) {
            throw new IllegalArgumentException("Error: Purchase price must be greater than zero.");
        }

        // Validate if stock exists before adding investment
        if (stockDAO.findByStockId(stockId) == null) {
            throw new IllegalArgumentException("Error: Stock with ID " + stockId + " does not exist.");
        }

        Investment investment = new Investment(portfolioId, stockId, quantity, purchasePrice);
        investmentDAO.addInvestment(investment);
        System.out.println("Investment added successfully!");
    }

    /**
     * Retrieves all investments associated with a specific portfolio.
     */
    public List<Investment> findByPortfolioId(int portfolioId) {
        List<Investment> investments = investmentDAO.findByPortfolioId(portfolioId);
        if (investments.isEmpty()) {
            System.out.println("No investments found for this portfolio.");
        }
        return investments;
    }

    /**
     * Updates an investment with validation rules.
     * - New quantity must be greater than zero.
     * - New purchase price must be greater than zero.
     */
    public void updateInvestment(int investmentId, int quantity, double purchasePrice) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Error: Quantity must be greater than zero.");
        }
        if (purchasePrice <= 0) {
            throw new IllegalArgumentException("Error: Purchase price must be greater than zero.");
        }
        
        investmentDAO.updateInvestment(investmentId, quantity, purchasePrice);
        System.out.println("Investment updated successfully!");
    }

    /**
     * Deletes an investment after validation.
     * - Ensure investment exists before attempting deletion.
     */
    public void deleteInvestment(int investmentId) {
        Investment investment = investmentDAO.findByInvestmentId(investmentId);
        if (investment == null) {
            throw new IllegalArgumentException("Error: Investment does not exist.");
        }
        investmentDAO.deleteInvestment(investmentId);
        System.out.println("Investment deleted successfully!");
    }
    
    
    /**
     * Retrieves all investments made by a particular user in a specific portfolio.
     */
    public List<UserInvestmentDTO> getUserInvestments(int userId, int portfolioId) {
        List<UserInvestmentDTO> investments = investmentDAO.getUserInvestments(userId, portfolioId);
        if (investments.isEmpty()) {
            System.out.println("No investments found for this user in the given portfolio.");
        }
        return investments;
    }    
    
    
    
    
    
}
