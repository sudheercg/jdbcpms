package com.codegnan.pms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 * Follows best practices for error handling and maintainability.
 */
public class DatabaseUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/portfolio_management";
    private static final String USER = "root";
    private static final String PASSWORD = "password";  // Change password accordingly

    /**
     * Establishes and returns a database connection.
     * Ensures meaningful error messages instead of stack traces.
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error: Unable to establish database connection. Please check database configuration.");
        }
    }
}