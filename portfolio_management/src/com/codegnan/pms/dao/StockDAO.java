package com.codegnan.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.codegnan.pms.model.Investor;
import com.codegnan.pms.model.Stock;
import com.codegnan.pms.util.DatabaseUtils;

/**
 * DAO class for handling stock-related database operations.
 */
public class StockDAO {
	public void createStock(Stock stock) {
		String sql = "INSERT INTO stocks (symbol, name, price) VALUES (?, ?, ?)";
		try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, stock.getSymbol());
			stmt.setString(2, stock.getName());
			stmt.setDouble(3, stock.getPrice());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error: Unable to create stock. Reason: " + e.getMessage());
		}
	}

	public Stock findByStockId(int stockId) {
		String sql = "SELECT * FROM stocks WHERE stock_id = ?";
		try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, stockId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Stock(rs.getInt("stock_id"), rs.getString("symbol"), rs.getString("name"),
						rs.getDouble("price"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error: Unable to retrieve stock. Reason: " + e.getMessage());
		}
		return null;
	}

	public List<Stock> findAllStocks() {
		List<Stock> stockList = new ArrayList<>();
		String sql = "SELECT * FROM stocks";
		try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				stockList.add(new Stock(rs.getInt("stock_id"), rs.getString("symbol"), rs.getString("name"),
						rs.getDouble("price")));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error: Unable to retrieve stock. Reason: " + e.getMessage());
		}
		return stockList;
	}

	public void updateStockPrice(int stockId, double newPrice) {
		String sql = "UPDATE stocks SET price = ? WHERE stock_id = ?";
		try (Connection conn = DatabaseUtils.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDouble(1, newPrice);
			stmt.setInt(2, stockId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error: Unable to update stock price. Reason: " + e.getMessage());
		}
	}

	public void deleteStock(int stockId) {
		try (Connection conn = DatabaseUtils.getConnection()) {
			// Step 1: Delete related investments first
			String deleteInvestmentsSQL = "DELETE FROM portfolio_investments WHERE stock_id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(deleteInvestmentsSQL)) {
				stmt.setInt(1, stockId);
				stmt.executeUpdate();
			}

			// Step 2: Delete stock if it has no dependencies
			String deleteStockSQL = "DELETE FROM stocks WHERE stock_id = ?";
			try (PreparedStatement stmt = conn.prepareStatement(deleteStockSQL)) {
				stmt.setInt(1, stockId);
				int rowsAffected = stmt.executeUpdate();
				if (rowsAffected == 0) {
					throw new RuntimeException("Error: Stock does not exist or is still referenced in portfolios.");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error: Unable to delete stock. Reason: " + e.getMessage());
		}
	}
}
