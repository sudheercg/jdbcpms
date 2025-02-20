package com.codegnan.pms.model;

/**
 * Represents a portfolio in the system.
 */
public class Portfolio {
    private int portfolioId;
    private int investorId;
    private String portfolioName;
    private String creationDate;

    public Portfolio(int portfolioId, int investorId, String portfolioName, String creationDate) {
        this.portfolioId = portfolioId;
        this.investorId = investorId;
        this.portfolioName = portfolioName;
        this.creationDate = creationDate;
    }
    
    
    //Use when Portfolio is auto-incremented
    public Portfolio(int investorId, String portfolioName, String creationDate) {
 
        this.investorId = investorId;
        this.portfolioName = portfolioName;
        this.creationDate = creationDate;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public int getInvestorId() {
        return investorId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public String getCreationDate() {
        return creationDate;
    }

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public void setPortfolioName(String portfolioName) {
		this.portfolioName = portfolioName;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Portfolio [portfolioId=" + portfolioId + ", investorId=" + investorId + ", portfolioName="
				+ portfolioName + ", creationDate=" + creationDate + "]";
	}
}