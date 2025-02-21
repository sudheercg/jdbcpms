package com.codegnan.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codegnan.pms.model.Investment;
import com.codegnan.pms.model.UserInvestmentDTO;
import com.codegnan.pms.util.DatabaseUtils;

/**
 * DAO class for handling investment-related database operations.
 */
public class InvestmentDAO {
    public void addInvestment(Investment investment) {
        String sql = "INSERT INTO portfolio_investments (portfolio_id, stock_id, quantity, purchase_price) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, investment.getPortfolioId());
            stmt.setInt(2, investment.getStockId());
            stmt.setInt(3, investment.getQuantity());
            stmt.setDouble(4, investment.getPurchasePrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to add investment. Reason: " + e.getMessage());
        }
    }

    public List<Investment> findByPortfolioId(int portfolioId) {
        List<Investment> investments = new ArrayList<>();
        String sql = "SELECT * FROM portfolio_investments WHERE portfolio_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, portfolioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                investments.add(new Investment(
                    rs.getInt("investment_id"),
                    rs.getInt("portfolio_id"),
                    rs.getInt("stock_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("purchase_price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to retrieve investments. Reason: " + e.getMessage());
        }
        return investments;
    }

    public Investment findByInvestmentId(int investmentId) {
        String sql = "SELECT * FROM portfolio_investments WHERE investment_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, investmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Investment(
                    rs.getInt("investment_id"),
                    rs.getInt("portfolio_id"),
                    rs.getInt("stock_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("purchase_price")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to retrieve investment. Reason: " + e.getMessage());
        }
        return null;
    }

    public void updateInvestment(int investmentId, int quantity, double purchasePrice) {
        String sql = "UPDATE portfolio_investments SET quantity = ?, purchase_price = ? WHERE investment_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setDouble(2, purchasePrice);
            stmt.setInt(3, investmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to update investment. Reason: " + e.getMessage());
        }
    }
    
    
    
    public List<UserInvestmentDTO> getUserInvestments(int userId, int portfolioId) {
        List<UserInvestmentDTO> investments = new ArrayList<>();
        String sql = "SELECT u.name AS investor_name, p.portfolio_name AS portfolio_name, s.name AS stock_name, " +
                     "i.quantity, i.purchase_price " +
                     "FROM investors u " +
                     "JOIN portfolio p ON u.investor_id = p.investor_id " +
                     "JOIN portfolio_investments i ON p.portfolio_id = i.portfolio_id " +
                     "JOIN stocks s ON i.stock_id = s.stock_id " +
                     "WHERE u.investor_id = ? AND p.portfolio_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, portfolioId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                investments.add(new UserInvestmentDTO(
                    rs.getString("investor_name"),
                    rs.getString("portfolio_name"),
                    rs.getString("stock_name"),
                    rs.getInt("quantity"),
                    rs.getDouble("purchase_price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to retrieve user investments. Reason: " + e.getMessage());
        }
        return investments;
    }

    
    
    
    
    
    
    

    public void deleteInvestment(int investmentId) {
        String sql = "DELETE FROM portfolio_investments WHERE investment_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, investmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to delete investment. Reason: " + e.getMessage());
        }
    }
}
