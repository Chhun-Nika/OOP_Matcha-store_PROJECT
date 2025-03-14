
public class Receipt {

    private static int receiptCounter = 0;
    private int receiptID;
    private int[] orderID;
    private double totalAmount;
    private double amountPaid;
    private double change;
    private String paymentMethod;

    // Constructor
    public Receipt(int[] orderID, double totalAmount, double amountPaid, double change, String paymentMethod) {
        this.receiptID = ++receiptCounter;
        this.orderID = orderID;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
        this.change = change;
        this.paymentMethod = paymentMethod;
    }

    // Getter 
    public int getReceiptID () {
        return receiptID;
    }

    public int [] getOrderID () {
        return orderID;
    }

    public double getTotalAmount () {
        return totalAmount;
    }

    public double getAmountPaid () {
        return amountPaid;
    }

    public double getChange () {
        return change;
    }

    public String getPaymentMethod () {
        return paymentMethod;
    }

    // Setter (for modifying private field with validation)

    // Ensure totalamount cannot be negative 
    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) {
            System.out.println("Error: Total amount cannot be negative.");
        } else {
            this.totalAmount = totalAmount;
        }
    }

    // Ensure amount paid is not negative and not less than the total amount
    public void setAmountPaid(double amountPaid) {
        if (amountPaid < 0) {
            System.out.println("Error: Amount paid cannot be negative.");
        } else if (amountPaid < totalAmount) {
            System.out.println("Error: Amount paid must be at least equal to the total amount.");
        } else {
            this.amountPaid = amountPaid;
            this.change = amountPaid - totalAmount; // Automatically calculate change
        }
    }

    // Change should be automatically updated when amount paid is set, so we don’t need a setter for it.

    // Allow modifying payment method
    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod.equalsIgnoreCase("Cash") || paymentMethod.equalsIgnoreCase("Credit Card")) {
            this.paymentMethod = paymentMethod;
        } else {
            System.out.println("Error: Invalid payment method. Use 'Cash' or 'Credit Card'.");
        }
    }
}

