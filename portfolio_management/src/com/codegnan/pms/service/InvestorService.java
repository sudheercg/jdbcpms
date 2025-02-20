package com.codegnan.pms.service;

import com.codegnan.pms.dao.InvestorDAO;
import com.codegnan.pms.model.Investor;

/**
 * Service class for managing investors.
 */
public class InvestorService {
    private final InvestorDAO investorDAO;

    public InvestorService() {
        this.investorDAO = new InvestorDAO();
    }

    public void createInvestor(String name, String email, String phone, String dateOfBirth) {
        if (name == null || email == null || phone == null || dateOfBirth == null) {
            throw new IllegalArgumentException("Error: All fields are required.");
        }
        Investor investor = new Investor(name, email, phone, dateOfBirth);
        investorDAO.createInvestor(investor);
        System.out.println("Investor created successfully!");
    }
}