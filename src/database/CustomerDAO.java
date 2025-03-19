package database;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

import models.Customer;



public class CustomerDAO {

    public static void register (String name , String phoneNumber,  String email ,String password, LocalDate dob) {
        Connection conn = null;
        PreparedStatement customerStmt = null;
        System.out.println("add from DAO");

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            String customerSQL = "INSERT INTO Customer (name, phone_number, email, password,dob) VALUES (?, ?, ?, ?, ?)";
            customerStmt = conn.prepareStatement(customerSQL, Statement.RETURN_GENERATED_KEYS);

            customerStmt.setString(1, name);
            customerStmt.setString(2, phoneNumber);
            customerStmt.setString(3, email);
            customerStmt.setString(4, password);
            customerStmt.setDate(5, Date.valueOf(dob));

            int affectedRows = customerStmt.executeUpdate();
            if (affectedRows > 0) {
                conn.commit();  
                System.out.println("Customer register successfully");
            } else {
                System.out.println("No Customer register!");
                conn.rollback();  
            }

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }

    private static HashMap<String, Customer> customerList = new HashMap<>();
    
    public static void LoadCustomer () {
        Connection conn = null;
        Statement stml = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            stml = conn.createStatement();
            String sql = "SELECT * FROM Customer";
            rs = stml.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("name");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String password = rs.getString("password");
                LocalDate dob = rs.getDate("dob").toLocalDate();

                Customer customer = new Customer(name , phoneNumber, email, password, dob);
                customerList.put(email, customer);

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
    
    public static Customer getCustomerByEmail(String email) {
        return customerList.get(email);
    }
}