package models;

import java.time.LocalDate;

public class Product {
    private static int idCounter = 0;
    private int productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private String productCategory;
    private int productQuantity;
    private String productSize;
    

    
    // constructor 
    // add new product for admin

        // for drinks
    public Product (String productName, String productDescription, double productPrice, String productCategory) {
        this.productId = ++idCounter;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
        // this.productSize = productSize;
    }

        // for deserts  
    public Product (String productName, String productDescription, double productPrice, String productCategory,  String productSize, int productQuantity) {
        this.productId = ++idCounter;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productSize = productSize;
        this.productQuantity = productQuantity;

    }



    // getter method for both seller and customers


    protected int getProductId() {
        return productId;
    }

    protected String getProductName() {
        return productName;
        
    };

    protected String getProductDescription () {
        return productDescription;
    };

    protected double getProductPrice () {
        return productPrice;
    };

    protected String getProductCategory () {
        return productCategory;
    };

    protected int getProductQuantity () {
        return productQuantity;
    };

    protected String getProductSize () {
        return productSize;
    }

    public static int getIdCounter() {
        return idCounter;
    }



    // setter method only for SELLER; this is used to 
    // - modify product detials (changing the price or other fields)
    // - updating the product stock (increasing and decreasing product quantity)
    protected void setProductName (String productName) {
        this.productName = productName;
    };

    protected void setProductPrice (double productPrice) {
        this.productPrice = productPrice;
    };

    protected void setProductQuantity (int productQuantity) {
        this.productQuantity = productQuantity;
    };

    protected void setProductDescription (String productDescription) {

        if (productDescription == null) {
            System.out.println("Description cannot be empty!");

        } else {
            this.productDescription = productDescription;
        }
    };

    protected void setProductCatergory (String productCatergory) {
        // catogories that are aviable in the matcha store
        String[] validCatergories = {"Drinks", "Desserts", "Retail"};

        // this is an enhanced for loop
        for(String catergory : validCatergories) {
            // equalsIgnoreCase is used to handle case-sensitive
            if (catergory.equals(productCatergory)) {
                this.productCategory = productCatergory;
            }
        }


    };

    protected void setSize (String productSize) {
        this.productSize = productSize;
    }
    
    protected static void setIdCounter (int id) {
        idCounter = id;
    }

    @Override
    // the format that is printed need to be change in order to display it in the console
    public String toString() {
        // Basic string concatenation using '+' operator
        String result = "Product: " +
                        "\n    Name: " + productName +
                        "\n    Description: " + productDescription  +
                        "\n    Price: " + productPrice +
                        "\n    Category: " + productCategory +
                        "\n    Size: " + productSize ;
    
        // Add quantity only if not a drink
        // equalsIgnoreCase is used to compare two string and ignore the case differences
        if (!productCategory.equalsIgnoreCase("Drinks")) {
            result += "\n    Quantity: " + productQuantity;
        }
    
        return result;
    }

    
    
    
}
