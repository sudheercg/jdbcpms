package com.codegnan.pms.model;

public class UserInvestmentDTO {
    private String investorName;
    private String portfolioName;
    private String stockName;
    private int quantity;
    private double purchasePrice;

    public UserInvestmentDTO(String investorName, String portfolioName, String stockName, int quantity, double purchasePrice) {
        this.investorName = investorName;
        this.portfolioName = portfolioName;
        this.stockName = stockName;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
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
        return "UserInvestmentDTO{" +
                "investorName='" + investorName + '\'' +
                ", portfolioName='" + portfolioName + '\'' +
                ", stockName='" + stockName + '\'' +
                ", quantity=" + quantity +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}
