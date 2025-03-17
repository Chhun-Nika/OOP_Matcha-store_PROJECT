package database;

import java.sql.*;

public class ProductDAO {

    public static void addNewProduct(String name, String description, double price, 
                                    String category, String size, double weight, Date expriryDate) {
        Connection conn = null;
        PreparedStatement productStmt = null;
        System.out.println("add from DAO");
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String productSQL = "INSERT INTO Product (name, description, price, category, product_size, weight, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            productStmt = conn.prepareStatement(productSQL, Statement.RETURN_GENERATED_KEYS);

            productStmt.setString(1, name);
            productStmt.setString(2, description);
            productStmt.setDouble(3, price);
            productStmt.setString(4, category);
            productStmt.setString(5, size);
            productStmt.setDouble(6, weight);
            productStmt.setDate(7, expriryDate);


            int affectedRows = productStmt.executeUpdate();
            if (affectedRows > 0) {
                conn.commit();  // Commit if insert was successful
                System.out.println("Product added successfully");
            } else {
                System.out.println("No product inserted");
                conn.rollback();  // Rollback if no rows were affected
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
    
}