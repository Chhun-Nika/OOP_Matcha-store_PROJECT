package models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import exception.*;



public class User {
    private HashMap<Integer, Customer> customerList;

    public User() {
        customerList = new HashMap<>();
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Register new customer: \n");
        String name, phoneNumber, email, address, password;
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

        // address
        while (true) {
            try {
                System.out.print("Address: ");
                address = scanner.nextLine();
                StringOnlyException.validate(address);
                break;
            } catch (StringOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        // password
        while (true) {
            try {
                System.out.print("Password: ");
                password = scanner.nextLine();
                StringOnlyException.validate(password);
                break;
            } catch (StringOnlyException e) {
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

        Customer customer = new Customer(name, address, phoneNumber, email, password, dateOfBirth);
        customerList.put(customer.getCustomerId(), customer);

    }
    public void customerList() {
        for(Customer customer : customerList.values()) {
            System.out.println(customer);
            System.out.println(customer.getCustomerId());
        }
    }
    public void saveToFile() {
        String pathName = "D:\\Year 2\\term 2\\OOP (objected oriented programming)\\Final Project\\OOP_FINAL-PROJECT_Y2-T2\\src\\database";
        String fileName = "user.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathName + "/" + fileName, true));
            for (Map.Entry<Integer, Customer> entry : customerList.entrySet()){
                Integer customerID = entry.getKey();
                Customer customer = entry.getValue();
    
                writer.write("ID: " + customerID + ",");
                writer.write("Name: " + customer.getCustomerName() + ",");
                writer.write("Address: " + customer.getCustomerAddress() + ",");
                writer.write("Phone Number: " + customer.getCustomerPhone() + ",");
                writer.write("Email: " + customer.getCustomerEmail() + ",");
                writer.write("DOB: " + customer.getDateofBirth());
                writer.newLine();
     
            }
            System.out.println("Customer data saved successfully.");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving customer data: " + e.getMessage());
        }
        
    }
}

