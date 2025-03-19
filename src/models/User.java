package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import database.CustomerDAO;
import database.SellerDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import exception.*;



public class User {
    private HashMap<Integer, Customer> customerList;

    public User() {
        customerList = new HashMap<>();
        CustomerDAO.LoadCustomer();
    }
    

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Register new customer: \n");
        String name, phoneNumber, email, password, confirmPassword;
        LocalDate dateOfBirth = null;

        // input and validate customer name
        while (true) {
            try {
                System.out.print("Name: ");
                name = scanner.nextLine();
                StringOnlyException.validate(name);
                break;
            } catch (StringOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        // emaill
        while (true) {
            try {
                System.out.print("Email: ");
                email = scanner.nextLine();
                EmailFormatException.validate(email);
                break;
            } catch (EmailFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        // phone number
        while (true) {
            try {
                System.out.print("Phone Number: ");
                phoneNumber = scanner.nextLine();
                PhoneNumberException.validate(phoneNumber);
                break;
            } catch (PhoneNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        // password
        while (true) {
            try {
                System.out.print("Password: ");
                password = scanner.nextLine();

                System.out.println("Comfirm Password: ");
                confirmPassword = scanner.nextLine();

                PasswordException.validate(password, confirmPassword);
                break;
            } catch (PasswordException e){
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("DateOfBirth:  (YYYY-MM-DD): ");
                String dateInput = scanner.nextLine();
                dateOfBirth = DateFormatException.validate(dateInput);
                break;
            } catch (DateFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        // Customer customer = new Customer(name, address, phoneNumber, email, password, dateOfBirth);
        // customerList.put(customer.getCustomerId(), customer);
        CustomerDAO.register(name, phoneNumber, email, confirmPassword, dateOfBirth);

        System.out.println("Customer registered successfully!");
    }
    public void customerList() {
        for(Customer customer : customerList.values()) {
            System.out.println(customer);
            System.out.println(customer.getCustomerId());
        }
    }

    public void loginCustomer () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Customer Login ");
        String email , password;

        while (true) {
            try {
                System.out.print("Email: ");
                email = scanner.nextLine();
                EmailFormatException.validate(email);
                break;
            } catch (EmailFormatException e){
                System.out.println(e.getMessage());
            }  
        }

        System.out.print("Password: ");
        password = scanner.nextLine();

        Customer customer = CustomerDAO.getCustomerByEmail (email);

        if (customer == null) {
            System.out.println("Error: Customer not found!");
            return;
        }
        if (customer != null && customer.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome, " + customer.getCustomerName());
        } else {
            System.out.println("Invalid password. Please try again.");
        }

    }


    public void loginSeller() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seller Login");

        String email, password;

        // Input validation for email
        while (true) {
            try {
                System.out.print("Email: ");
                email = scanner.nextLine();
                EmailFormatException.validate(email);
                break;
            } catch (EmailFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Password: ");
        password = scanner.nextLine();

    
        SellerDAO.loadSeller();
        String storedPassword = SellerDAO.getSellerPasswordByEmail(email);

        if (storedPassword == null) {
            System.out.println("Error: Seller not found!");
            return;
        }

        if (storedPassword.equals(password)) {
            System.out.println("Login successful! Welcome, Seller.");
        } else {
            System.out.println("Invalid password. Please try again.");
        }
    }



    public void saveToFile() {
        String pathName = "D:\\Year 2\\term 2\\OOP (objected oriented programming)\\Final Project\\OOP_Matcha-store_PROJECT\\src\\database";
        String fileName = "user.txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathName + "/" + fileName, true))) {
            for (Map.Entry<Integer, Customer> entry : customerList.entrySet()) {
                Integer customerID = entry.getKey();
                Customer customer = entry.getValue();
    
                writer.write("ID:" + customerID + ",");
                writer.write("Name:" + customer.getCustomerName() + ",");
                writer.write("Phone Number:" + customer.getCustomerPhone() + ",");
                writer.write("Email:" + customer.getCustomerEmail() + ",");
                writer.write("DOB:" + customer.getDateofBirth());
              writer.newLine();
            }
            System.out.println("Customer data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }        
    }
    

    
    public void loadCustomerFromFile() {
        customerList.clear(); // Clear existing data before loading
    
        String pathName = "D:\\Year 2\\term 2\\OOP (objected oriented programming)\\Final Project\\OOP_Matcha-store_PROJECT\\src\\database";
        String fileName = "user.txt";
    
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName + "/" + fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split line into parts based on ","
                String[] parts = line.split(",");
    
                if (parts.length < 6) { 
                    System.out.println("Invalid data format: " + line);
                    continue;
                }
    
                // Extract values after ":"
                int customerID = Integer.parseInt(parts[0].split(":")[1].trim());
                String name = parts[1].split(":")[1].trim();
                String phoneNumber = parts[2].split(":")[1].trim();
                String email = parts[3].split(":")[1].trim();
                LocalDate dateOfBirth = LocalDate.parse(parts[4].split(":")[1].trim());
    
                // Create Customer object and store it in the HashMap
                Customer customer = new Customer(name, phoneNumber, email, "", dateOfBirth);
                customerList.put(customerID, customer);
            }
            System.out.println("Customer data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading customer data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error parsing customer data: " + e.getMessage());
        }
    }
}
    
