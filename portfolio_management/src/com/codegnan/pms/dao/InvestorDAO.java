package com.codegnan.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codegnan.pms.model.Investor;
import com.codegnan.pms.util.DatabaseUtils;

/**
 * DAO class for handling investor-related database operations.
 */
public class InvestorDAO {
    public void createInvestor(Investor investor) {
        String sql = "INSERT INTO investors (name, email, phone, date_of_birth) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, investor.getName());
            stmt.setString(2, investor.getEmail());
            stmt.setString(3, investor.getPhone());
            stmt.setString(4, investor.getDateOfBirth());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to create investor. Reason: " + e.getMessage());
        }
    }

    public Investor findById(int investorId) {
        String sql = "SELECT * FROM investors WHERE investor_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, investorId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Investor(
                    rs.getInt("investor_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("date_of_birth")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to retrieve investor. Reason: " + e.getMessage());
        }
        return null;
    }

    public List<Investor> findAll() {
        List<Investor> investors = new ArrayList<>();
        String sql = "SELECT * FROM investors";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                investors.add(new Investor(
                    rs.getInt("investor_id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("date_of_birth")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to retrieve investors. Reason: " + e.getMessage());
        }
        return investors;
    }

    public void updateInvestor(Investor investor) {
        String sql = "UPDATE investors SET name = ?, email = ?, phone = ?, date_of_birth = ? WHERE investor_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, investor.getName());
            stmt.setString(2, investor.getEmail());
            stmt.setString(3, investor.getPhone());
            stmt.setString(4, investor.getDateOfBirth());
            stmt.setInt(5, investor.getInvestorId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to update investor. Reason: " + e.getMessage());
        }
    }

    public void deleteInvestor(int investorId) {
        String sql = "DELETE FROM investors WHERE investor_id = ?";
        try (Connection conn = DatabaseUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, investorId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error: Unable to delete investor. Reason: " + e.getMessage());
        }
    }
}
