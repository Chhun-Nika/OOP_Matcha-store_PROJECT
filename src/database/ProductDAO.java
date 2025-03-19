package database;

import java.sql.*;
import java.time.LocalDate;

import models.Product;
import models.RetailProduct;
import models.Store;

public class ProductDAO {

    public static void addNewProduct(String name, String description, double price, 
                                    String category, String size, double weight, LocalDate expriryDate, int quantity) {
        Connection conn = null;
        PreparedStatement productStmt = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String productSQL = "";
            switch (category) {
                case "DRINK" :
                    productSQL = "INSERT INTO Product (name, description, price, category) VALUES (?, ?, ?, ?)";
                    break;
                case "DESSERT" :
                    productSQL = "INSERT INTO Product (name, description, price, category, product_size, quantity) VALUES (?, ?, ?, ?, ?, ?)";
                    break;
                case "RETAIL" :
                    productSQL = "INSERT INTO Product (name, description, price, category, product_size, weight, expiry_date, quantity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    break;
                default:
                    System.out.println("Invalid Category");
                    return;
            }

            productStmt= conn.prepareStatement(productSQL, Statement.RETURN_GENERATED_KEYS);
            productStmt.setString(1, name);
            productStmt.setString(2, description);
            productStmt.setDouble(3, price);
            productStmt.setString(4, category);
            
            if (category.equals("DESSERT")) {
                productStmt.setString(5, size);
                productStmt.setInt(6, quantity);
            } else if (category.equals("RETAIL")) {
                productStmt.setString(5, size);
                productStmt.setDouble(6, weight);
                productStmt.setDate(7, Date.valueOf(expriryDate));
                productStmt.setInt(8, quantity);
            }

            int affectedRows = productStmt.executeUpdate();
            if (affectedRows > 0) {
                conn.commit();
                System.out.println("Product added successfully");
            } else {
                System.out.println("No product inserted");
                conn.rollback();
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

    public static void loadProductFromDB(Store store) {


        Connection conn = null;

        // create a string to query data
        String query = "SELECT id, name, description, price, category, product_size, weight, expiry_date, quantity FROM Product";

        try {
            conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");
                String category = result.getString("category");
                String product_size = result.getString("product_size");
                double weight = result.getDouble("weight");

                // ensure that the date in data base is not null before converting it into localDate in java
                Date sqlDate = result.getDate("expiry_date");
                LocalDate expiryDate = null;
                if (sqlDate != null) expiryDate = sqlDate.toLocalDate();


                int quantity = result.getInt("quantity");

                Product product = null;

                if (category.equals("DRINK")) {
                    product = new Product(id, name, description, price, category, product_size, quantity);
                } else if (category.equals("DESSERT")) {
                    product = new Product(id, name, description, price, category, product_size, quantity);
                } else if (category.equals("RETAIL")) {
                    product = new RetailProduct(name, description, price, category, product_size, quantity, weight, expiryDate);
                }

                if (product != null) store.addProduct(id, product);
                System.out.println("product loaded sucessfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}