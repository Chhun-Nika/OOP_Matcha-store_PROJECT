import java.io.Serializable;
public class Order implements Serializable{
    
    //private static final long Serializable = 1L;
    private static int nextOrderID = 1;
    private int orderID;
    private int customerID;
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
}
