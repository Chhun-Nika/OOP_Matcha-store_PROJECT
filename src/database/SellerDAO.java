package database;


import java.sql.*;
import java.util.HashMap;

import models.Customer;

public class SellerDAO {

    private static HashMap<String, String> sellerList = new HashMap<>();

    public static void loadSeller() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stmt = (Statement) conn.createStatement();
            String sql = "SELECT * From Seller"; 
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                sellerList.put(email, password);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    public static String getSellerPasswordByEmail(String email) {
        return sellerList.get(email);
    }
}
    
