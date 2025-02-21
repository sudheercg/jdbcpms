package com.codegnan.pms.client;

import java.util.List;

import com.codegnan.pms.model.Investment;
import com.codegnan.pms.model.UserInvestmentDTO;
import com.codegnan.pms.service.InvestmentService;

/**
 * Client class to test CRUD operations for InvestmentService.
 */
public class InvestmentClient {
    public static void main(String[] args) {
        InvestmentService investmentService = new InvestmentService();

        // Add new investments
        System.out.println("Adding investments to portfolio...");
        investmentService.addInvestment(1, 1, 50, 150.75);
        investmentService.addInvestment(1, 2, 20, 200.50);
        System.out.println("Investments added successfully.\n");

        // Retrieve all investments for a portfolio
        System.out.println("Retrieving investments for portfolio ID 1...");
        List<Investment> investments = investmentService.findByPortfolioId(1);
        for (Investment investment : investments) {
            System.out.println(investment);
        }
        System.out.println();

        // Update an investment
        System.out.println("Updating investment with ID 1...");
        investmentService.updateInvestment(1, 100, 175.25);
        System.out.println("Investment updated successfully.\n");

        // Retrieve updated investments
        System.out.println("Retrieving updated investments for portfolio ID 1...");
        investments = investmentService.findByPortfolioId(1);
        for (Investment investment : investments) {
            System.out.println(investment);
        }
        System.out.println();

        // Delete an investment
        System.out.println("Deleting investment with ID 2...");
      //  investmentService.deleteInvestment(2);
        System.out.println("Investment deleted successfully.\n");
        
        
        
        
        
        List<UserInvestmentDTO> investmentsList1 = investmentService.getUserInvestments(1, 1);

        if (investments.isEmpty()) {
            System.out.println("No investments found for the provided details.");
        } else {System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println("Investments for User ID " + 1 + " in Portfolio ID " + 1 + ":");
            for (UserInvestmentDTO investment : investmentsList1) {
                System.out.println("Investor: " + investment.getInvestorName() +
                        ", Portfolio: " + investment.getPortfolioName() +
                        ", Stock: " + investment.getStockName() +
                        ", Quantity: " + investment.getQuantity() +
                        ", Purchase Price: $" + investment.getPurchasePrice());
            }
        }

        
        
        
        
        
        
    }
}
