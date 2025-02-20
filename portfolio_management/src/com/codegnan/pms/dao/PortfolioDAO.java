package com.codegnan.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codegnan.pms.model.Portfolio;
import com.codegnan.pms.util.DatabaseUtils;

/**
 * DAO class for handling portfolio-related database operations.
 */
public class PortfolioDAO {
    public void createPortfolio(Portfolio portfolio) {
        String sql = "INSERT INTO portfolio (investor_id, portfolio_name, creation_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, portfolio.getInvestorId());
            stmt.setString(2, portfolio.getPortfolioName());
            stmt.setString(3, portfolio.getCreationDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to create portfolio. Reason: " + e.getMessage());
        }
    }

    public List<Portfolio> findByInvestorId(int investorId) {
        List<Portfolio> portfolios = new ArrayList<>();
        String sql = "SELECT * FROM portfolio WHERE investor_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, investorId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                portfolios.add(new Portfolio(
                    rs.getInt("portfolio_id"),
                    rs.getInt("investor_id"),
                    rs.getString("portfolio_name"),
                    rs.getString("creation_date")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to retrieve portfolios. Reason: " + e.getMessage());
        }
        return portfolios;
    }

    public Portfolio findByPortfolioId(int portfolioId) {
        String sql = "SELECT * FROM portfolio WHERE portfolio_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, portfolioId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Portfolio(
                    rs.getInt("portfolio_id"),
                    rs.getInt("investor_id"),
                    rs.getString("portfolio_name"),
                    rs.getString("creation_date")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to retrieve portfolio. Reason: " + e.getMessage());
        }
        return null;
    }

    public void updatePortfolioName(int portfolioId, String newName) {
        String sql = "UPDATE portfolio SET portfolio_name = ? WHERE portfolio_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newName);
            stmt.setInt(2, portfolioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to update portfolio name. Reason: " + e.getMessage());
        }
    }

    public void deletePortfolio(int portfolioId) {
        try (Connection conn = DatabaseUtils.getConnection()) {
            // Step 1: Delete related investments first
            String deleteInvestmentsSQL = "DELETE FROM portfolio_investments WHERE portfolio_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deleteInvestmentsSQL)) {
                stmt.setInt(1, portfolioId);
                stmt.executeUpdate();
            }

            // Step 2: Delete portfolio if it has no dependencies
            String deletePortfolioSQL = "DELETE FROM portfolio WHERE portfolio_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(deletePortfolioSQL)) {
                stmt.setInt(1, portfolioId);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new RuntimeException("Error: Portfolio does not exist or is still referenced in investments.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to delete portfolio. Reason: " + e.getMessage());
        }
    }
}
