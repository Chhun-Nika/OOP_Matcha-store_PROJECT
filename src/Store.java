import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import exception.*;

public class Store {
    private HashMap<Integer, Product> productList;
    private int lastProductID;

    // Constructor to initialize the hashmap
    public Store() {
        productList = new HashMap<>();
    }

    public void addProduct(Product product) {
        int lastProductID = product.getProductId();
        productList.put(lastProductID, product);
        Product.setIdCounter(lastProductID);
        
    }


    public void inputNewProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new product:\n");

        String name, description, category, size;
        double price, weight = 0;
        int quantity = 0;
        LocalDate expiryDate = null;

        // Input & Validation for Name
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

        // Input & Validation for Description
        while (true) {
            try {
                System.out.print("Description: ");
                description = scanner.nextLine();
                StringOnlyException.validate(description);
                break;
            } catch (StringOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        // Input & Validation for Category
        while (true) {
            try {
                System.out.print("Category: ");
                category = scanner.nextLine();
                StringOnlyException.validate(category);
                break;
            } catch (StringOnlyException e) {
                System.out.println(e.getMessage());
            }
        }

        // Input & Validation for Price
        while (true) {
            try {
                System.out.print("Price: ");
                String priceInput = scanner.nextLine();
                PositiveDecimalException.validate(priceInput);
                price = Double.parseDouble(priceInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.print("Size: ");
        size = scanner.nextLine();

        // while (true) {
        //     try {

        //     }
        // }

        Product product;

        if (category.equalsIgnoreCase("Drinks")) {
            product = new Product(name, description, price, category, size);
            addProduct(product);

        } else if (category.equalsIgnoreCase("Dessert")) {
            while (true) {
                try {
                    System.out.print("Quantity: ");
                    quantity = Integer.parseInt(scanner.nextLine());
                    if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid quantity! Please enter a valid positive number.");
                }
            }

            product = new Product(name, description, price, category, size, quantity);
            addProduct(product);

        } else if (category.equalsIgnoreCase("Retail product")) {
            while (true) {
                try {
                    System.out.print("Quantity: ");
                    String inputQuantity = scanner.nextLine();
                    PositiveIntegerException.validate(inputQuantity);
                    quantity = Integer.parseInt(inputQuantity);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid quantity! Please enter a valid positive number.");
                }
            }

            while (true) {
                try {
                    System.out.print("Weight: ");
                    String inputWeight = scanner.nextLine();
                    PositiveDecimalException.validate(inputWeight);
                    weight = Double.parseDouble(inputWeight);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid weight! Please enter a valid positive number.");
                }
            }

            while (true) {
                try {
                    System.out.print("Expiry Date (YYYY-MM-DD): ");
                    String dateInput = scanner.nextLine();
                    expiryDate = DateFormatException.validate(dateInput);
                    break;
                } catch (DateFormatException e) {
                    System.out.println(e.getMessage());
                }
            }

            product = new RetailProduct(name, description, price, category, size, quantity, weight, expiryDate);
            addProduct(product);
        }
        // scanner.close();
    }

    public Product getProduct(int productId) {
        return productList.get(productId);
    }

    public void listProducts() {
        
        for (Product product : productList.values()) {
            System.out.println(product);
            // System.out.println(product.getProductId());
        }
        
    }


    // write data from hashMap into file 
    public void saveProductsToFile() {
        
        String pathName = "/Users/nikachhun/Documents/Year 2/Term 2/Java OOP/Java_Project/OOP_FINAL-PROJECT_Y2-T2/src/database";
        String fileName = "product.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                pathName + "/" + fileName, true));

            // Iterate through the entrySet to access both the key (ID) and value (Product)
            for (Map.Entry<Integer, Product> entry : productList.entrySet()) {
                Integer productId = entry.getKey(); // Get the product ID (key)
                Product product = entry.getValue(); // Get the product (value)

                // Write product details to the file
                writer.write("ID: " + productId + ", ");
                writer.write("Name: " + product.getProductName() + ", ");
                writer.write("Description: " + product.getProductDescription() + ", ");
                writer.write("Price: " + product.getProductPrice() + ", ");
                writer.write("Category: " + product.getProductCategory() + ", ");

                // Handle Size for "Drinks" and other categories
                if (product.getProductCategory().equalsIgnoreCase("Drinks")) {
                    writer.write("Size: " + product.getProductSize());
                } else {
                    writer.write("Size: " + product.getProductSize() + ", ");
                }

                // Handle Quantity for "Dessert" and RetailProduct
                if (product.getProductCategory().equalsIgnoreCase("Dessert")) {
                    writer.write("Quantity: " + product.getProductQuantity() + ", ");
                } else if (product instanceof RetailProduct) {
                    RetailProduct retailProduct = (RetailProduct) product;
                    writer.write("Quantity: " + retailProduct.getProductQuantity() + ", ");
                    writer.write("Weight: " + retailProduct.getWeight() + ", ");
                    writer.write("ExpiryDate: " + retailProduct.getExpiryDate() + ", ");
                }

                writer.newLine(); // Write a new line after each product entry
            }
            writer.close(); // Close the BufferedWriter

            System.out.println("Success");

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
}

    public void loadProductsFromFile() {
        productList.clear();

        String pathName = "/Users/nikachhun/Documents/Year 2/Term 2/Java OOP/Java_Project/OOP_FINAL-PROJECT_Y2-T2/src/database";
        String fileName = "product.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathName + "/" + fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line by ", " to extract individual attributes
                String[] parts = line.split(", ");
    
                // Further split by ":" to get the value part (after the ":")
                // String id = parts[0].split(":")[1].trim();
                String name = parts[1].split(":")[1].trim();
                String description = parts[2].split(":")[1].trim();
                double price = Double.parseDouble(parts[3].split(":")[1].trim());
                String category = parts[4].split(":")[1].trim();
                String size = parts[5].split(":")[1].trim();
    
                // Declare product variable
                Product product;
    
                if (category.equalsIgnoreCase("Retail product")) {
                    // Extract RetailProduct-specific fields
                    int quantity = Integer.parseInt(parts[6].split(":")[1].trim());
                    double weight = Double.parseDouble(parts[7].split(":")[1].trim());
                    LocalDate expiryDate = LocalDate.parse(parts[8].split(":")[1].trim());
    
                    // Create RetailProduct object
                    product = new RetailProduct(name, description, price, category, size, quantity, weight, expiryDate);
    
                } else if (category.equalsIgnoreCase("Dessert")) {
                    // Extract Dessert-specific field (quantity)
                    int quantity = Integer.parseInt(parts[6].split(":")[1].trim());
    
                    // Create Dessert object (or general Product if Dessert class doesn't exist)
                    product = new Product(name, description, price, category, size, quantity);
    
                } else {
                    // For other categories (e.g., Drinks), create a regular Product
                    product = new Product(name, description, price, category, size);
                }
    
                // Add the product to the product list using its product ID
                productList.put(product.getProductId(), product);
            }
            reader.close();
            
        } catch (IOException e) {
            System.out.println("An error occurred while reading from the file: " + e.getMessage());
        }
    }

    // get the last ID from file
    private int loadLastProductID() {
        String pathName = "/Users/nikachhun/Documents/Year 2/Term 2/Java OOP/Java_Project/OOP_FINAL-PROJECT_Y2-T2/src/database";
        String fileName = "test.txt";
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(pathName + "/" + fileName));
            String line;
            String lastLine = null;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            String[] parts = lastLine.split(", ");
            lastProductID = Integer.parseInt(parts[0].split(":")[1].trim());
            reader.close();

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        return lastProductID;
    }


}