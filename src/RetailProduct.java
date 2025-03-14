
import java.time.LocalDate;

public class RetailProduct extends Product {

    private double weight;
    private LocalDate expiryDate;

    // constructor for matcha powder
    public RetailProduct(String productName,  String productDescription, double productPrice, String productCategory, String productSize, int productQuantity,
                            double weight, LocalDate dateInput) {
        super(productName, productDescription, productPrice, productCategory, productSize, productQuantity);
        this.weight = weight;
        this.expiryDate = dateInput;
    }

 
    // getter method for seller and customer
    // the override one are the functions from the superclass
    // without the protected in the superclass, in order to access to the fields in the super class we need to used the super function
    


    protected double getWeight () {
        return weight;
    }

    protected LocalDate getExpiryDate () {
        return expiryDate;
    }



    // setter method only for SELLER; this is used to 
    // - modify product details (changing the price or other fields)
    // - updating the product stock (increasing and decreasing product quantity)

    protected void setWeight (double weight) {
        this.weight = weight;
    }

    protected void setExpiryDate (LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n    Weight " + weight 
                                + "\n    Expiry date: " + expiryDate;
    }

}
