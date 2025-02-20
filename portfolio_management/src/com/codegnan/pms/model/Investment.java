package com.codegnan.pms.model;

/**
 * Represents an investment in the system.
 */
public class Investment {
    private int investmentId;
    private int portfolioId;
    private int stockId;
    private int quantity;
    private double purchasePrice;

    /**
     * Constructor for Investment.
     */
    public Investment(int investmentId, int portfolioId, int stockId, int quantity, double purchasePrice) {
        this.investmentId = investmentId;
        this.portfolioId = portfolioId;
        this.stockId = stockId;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public Investment(int portfolioId, int stockId, int quantity, double purchasePrice) {
        this(0, portfolioId, stockId, quantity, purchasePrice);
    }

    public int getInvestmentId() {
        return investmentId;
    }

    public void setInvestmentId(int investmentId) {
        this.investmentId = investmentId;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Override
    public String toString() {
        return "Investment [investmentId=" + investmentId + ", portfolioId=" + portfolioId + ", stockId=" + stockId + ", quantity=" + quantity + ", purchasePrice=" + purchasePrice + "]";
    }
}

