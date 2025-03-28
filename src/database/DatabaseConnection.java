package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.text.ListFormat.Style;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import com.mysql.cj.exceptions.CJCommunicationsException;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import models.Product;
import models.RetailProduct;

import java.sql.ResultSet;

public class DatabaseConnection {

    public static Connection connection = null;
    private static final String URL = "jdbc:mysql://localhost:3306/Matcha_store";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "8@ddeth*";

    // Establish the connection
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected to MySQL successfully!");
            }catch (CJCommunicationsException e) {
                System.out.println("please check ur db server");
               
            }
            catch (CommunicationsException e) {
                System.out.println("please check ur db server");
                
            }
            catch (SQLSyntaxErrorException e) {
                System.out.println("Connection failed!");
                
            }
            catch (SQLException e) {
                System.out.println("Connection failed!");
                
            }
        }
        return connection;
    }

    // Execute a query (SELECT)
    public static ResultSet executeQuery(String query) {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Query execution failed!");
            e.printStackTrace();
        }
        return null;
    }

    // Execute an update (INSERT, UPDATE, DELETE)
    public static int executeUpdate(String query) {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println("Update execution failed!");
            e.printStackTrace();
        }
        return 0;
    }

    // Close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Failed to close the connection!");
                e.printStackTrace();
            }
        }
    }

    public static HashMap<Integer, RetailProduct> getProducts() throws SQLException {
        getConnection();
        Statement stm = connection.createStatement();
        System.out.println("Getting values from product...");
        HashMap<Integer, RetailProduct> productMap = new HashMap<>();
        String sql = "SELECT * FROM product"; // Adjust table/column names as needed
        ResultSet rs = stm.executeQuery(sql);
        while (rs != null && rs.next()) {
            Integer productId = rs.getInt("id");
            String productName = rs.getString("name");
            String productDescription = rs.getString("description");
            double productPrice = rs.getDouble("price");
            String productCategory = rs.getString("category");
            int productQuantity = rs.getInt("quantity");
            String productSize = rs.getString("product_size");
            double weight = rs.getDouble("weight");
            LocalDate expiryDate = rs.getDate("expiry_date") != null ? rs.getDate("expiry_date").toLocalDate() : null;  // Handle null dates

            RetailProduct newProduct = new RetailProduct(productName, productDescription, productPrice, productCategory, productSize, productQuantity, weight, expiryDate);
            
            productMap.put(productId, newProduct);
        }
        closeConnection();
        return productMap;
    }

    // public static void main(String[] args) {
    //     HashMap<String, Product> products = getProducts();
    //     products.forEach((key, value) -> System.out.println(key + " -> " + value));
    // }

    

    public static void  insertToCart(int customerID, int productID, String productName, double unitPrice, double totalPrice) throws SQLException{
        // Test the connection
        getConnection();
        Statement stm = connection.createStatement();
        // Executing the SQL Query
        String sql = "INSERT INOT cart ()";  // Syntax of MySQL data insertion
        
        stm.executeUpdate(sql);
    
        System.out.println("Insert Complete!!");

        closeConnection();
    }

    public static void main(String[] args) throws SQLException {
        HashMap<Integer, RetailProduct> listOfProduct = new HashMap<>();
        Scanner input = new Scanner(System.in);
        listOfProduct =  getProducts();
        int productID;

        System.out.println("Please Choose product ID: ");
        productID = Integer.valueOf(input.nextLine());

        if(listOfProduct.get(productID) != null) {

            System.out.println(listOfProduct.get(productID));
        } else {
            System.out.println("Product is not available.");
        }
        
    }
    
}
 