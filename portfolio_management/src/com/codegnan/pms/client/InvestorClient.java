package com.codegnan.pms.client;

import com.codegnan.pms.dao.InvestorDAO;
import com.codegnan.pms.model.Investor;
import java.util.List;

/**
 * Client class to test CRUD operations for InvestorDAO.
 */
public class InvestorClient {
    public static void main(String[] args) {
        InvestorDAO investorDAO = new InvestorDAO();

        // Create new investors
        System.out.println("Creating investors...");
       // investorDAO.createInvestor(new Investor("Sudheer Y ", "sudheer@codegnan.com", "9876543210", "1985-06-15"));
        //investorDAO.createInvestor(new Investor("Prajeeth Nathan", "prajeeth@apollo.com", "9876543410", "1990-09-25"));
       // System.out.println("Investors created successfully.\n");

        // Retrieve an investor by ID
        System.out.println("Retrieving investor with ID 1...");
        Investor investor = investorDAO.findById(1);
        if (investor != null) {
            System.out.println("Investor Found: " + investor);
        } else {
            System.out.println("Investor not found.");
        }
        System.out.println();

        // Retrieve all investors
        System.out.println("Retrieving all investors...");
        List<Investor> investors = investorDAO.findAll();
        for (Investor inv : investors) {
            System.out.println(inv);
        }
        System.out.println();

        // Update an investor
        System.out.println("Updating investor with ID 1...");
        investorDAO.updateInvestor(new Investor(1, "Roshan John", "john.updated@example.com", "1122334455", "1985-06-15"));
        System.out.println("Investor updated successfully.\n");

        // Retrieve updated investor
        System.out.println("Retrieving updated investor with ID 1...");
        Investor updatedInvestor = investorDAO.findById(1);
        if (updatedInvestor != null) {
            System.out.println("Updated Investor: " + updatedInvestor);
        }
        System.out.println();

        // Delete an investor
         // System.out.println("Deleting investor with ID 2...");
         //investorDAO.deleteInvestor(2);
         //System.out.println("Investor deleted successfully.\n");
    }
}
