package models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthEditorPaneUI;

import database.DatabaseConnection;
public class Order implements Serializable{
    
    //private static final long Serializable = 1L;
    private static int nextOrderID = 1;
    private int orderID;
    private int customerID = 1;
    private String menuID;
    private int quantity;
    private double unitPrice;
    private double totalPrice; //subtotal
    private String orderNote; //special instructions
    
        // Constructor
    public Order(int customerID, int quantity, String menuID, double totalPrice, double unitPrice, String orderNote) {
        
        try {
            if(menuID == null || menuID.isEmpty()){
                throw new IllegalArgumentException("ID menu cannot be null or empty");
            }
            if(quantity <= 0){
                throw new IllegalAccessException("Quantity must be greater than zero");
            }
            if(unitPrice <= 0){
                throw new IllegalAccessException("Price must be bigger than zero");
            }
            
            this.orderID = nextOrderID + 1;
            this.customerID = customerID;
            this.menuID = menuID;
            this.quantity = quantity;
            this.totalPrice = totalPrice;
            this.unitPrice = unitPrice;
            this.orderNote = orderNote;

        } catch (IllegalAccessException e) {
            System.out.println("Order have been failed: "+ e.getMessage());
        }
    }
    
    public void addToCart(String productId, int quantity) {
        if(productId == null || productId.isEmpty() || quantity <= 0) {
            System.out.println("Invalid product or quantity.");
            return;
        }

    
        recalculateTotalPrice();
        System.out.println((quantity + " x " + productId + "added to cart."));
    }
    
    public int getOrderId(){
        return orderID;
    }
    
    public String getItem(){
        return menuID;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity(int quantity){
        try {
            if(quantity <= 0){
                throw new IllegalAccessException("Quantity must be not zero.");
            }

            this.quantity = quantity;
            recalculateTotalPrice();

        } catch (IllegalAccessException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public void setItem(String menuID){
        this.menuID = menuID;
        this.unitPrice = getUnitPrice(menuID);
        recalculateTotalPrice(); //why it indefind for type order?
    }
    
    private void recalculateTotalPrice(){
        double unitPrice = getUnitPrice(menuID);
        this.totalPrice = unitPrice * this.quantity;
    }
    
    private double getUnitPrice(String menuID){
        if(menuID.equals("MATCHA_LATTE")) return 5.50;
        if(menuID.equals("HOT_LATTE")) return 3.15;
        return 4.00; //default price
    }
    
    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
        recalculateTotalPrice();
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice){
        this.totalPrice = totalPrice;
    }

    public void setOrderNote(String orderNote){
        this.orderNote = orderNote;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderID + ", Item: " + menuID + ", Quantity: " + quantity +
               ", Unit Price: $" + unitPrice + ", Total Price: $" + totalPrice +
               ", Note: " + orderNote;
    }


 public HashMap<Integer, RetailProduct> getProducts() throws SQLException {
        DatabaseConnection.getConnection();
        Statement stm = DatabaseConnection.connection.createStatement();
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
        DatabaseConnection.closeConnection();
        return productMap;
    }

        public void insertDataToCart(int productID, String productName, int quantity, double unitPrice, double totalPrice) {
            try {
                DatabaseConnection.getConnection();
                Statement stm = DatabaseConnection.connection.createStatement();
                String sql = "INSERT INTO cart (customer_id, product_id, product_name, quantity, unit_price, total_price) VALUES (" +
                    this.customerID + ", " + productID + ", '" + productName + "', " + quantity + ", " + unitPrice + ", " + totalPrice + ")";
                stm.executeUpdate(sql);
                System.out.println("Product added to cart successfully.");
                DatabaseConnection.closeConnection();
            } catch (SQLException e) {
                System.out.println("Error inserting data into cart: " + e.getMessage());
            }
        }

        public void addToCart() throws SQLException {
        HashMap<Integer, RetailProduct> listOfProduct = new HashMap<>();
        Scanner input = new Scanner(System.in);
        listOfProduct =  this.getProducts();
        int productID;
        int numberOfList;
        int quantity = 0;
        double total;
        String productName;
        double unitPrice;
        System.out.println("Choose how many product would you like to buy: ");
        numberOfList = Integer.valueOf(input.nextLine());

        for(int i = 0; i < numberOfList; i ++) {
            System.out.println("Please Choose product ID: ");
            productID = Integer.valueOf(input.nextLine());
            if(listOfProduct.get(productID) != null) {
                RetailProduct selectedProduct = listOfProduct.get(productID);
                productName = selectedProduct.getProductName();
                System.out.println("Selected Product: " + productName);
                System.out.println("How many quantity would you like: ");
                quantity = Integer.valueOf(input.nextLine());
                unitPrice = selectedProduct.getProductPrice();
                total = unitPrice * quantity;
                System.out.println("Total for " + selectedProduct.getProductName() + ": $" + total);
                insertDataToCart(productID, productName, quantity, unitPrice, total);
            } else {
                System.out.println("Product is not available.");
                i --;
            }
        }
    

    }

    public static void main(String[] args) throws SQLException {
        Order newOrder = new Order(1, 2, "MATCHA_LATTE", 11.0, 5.50, "No sugar");
        HashMap<Integer, RetailProduct> product = new HashMap<>();
        newOrder.addToCart();
        // product = newOrder.getProducts();
        // System.out.println(product);
    }
}
