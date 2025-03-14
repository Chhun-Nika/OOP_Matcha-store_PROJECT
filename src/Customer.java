import java.util.HashMap;
import java.time.LocalDate;
import java.util.Date;


public class Customer implements Interface {

    private static int customerIDCounter = 0; //i want to use this for count my customer by giving id and update more and more

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private String password;

    private LocalDate dateOfBirth; //Date i dicide to use this because it error and i just test it


    private static  HashMap <String , Customer> customers = new HashMap<>();

    // constructor for register

    public Customer( String customerName, String customerAddress, String customerPhone, String customerEmail, String password, LocalDate dateOfBirth) {

        this.customerID = ++ customerIDCounter;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public Customer () {

    }

    // Getter 
    public int getCustomerId() {
        return customerID;
    }
    public String getCustomerName () {
        return customerName;
    }

    public String getCustomerAddress () {
        return customerAddress;
    }

    public String getCustomerPhone () {
        return customerPhone;
    }

    public String getCustomerEmail () {
        return customerEmail;
    }


    public LocalDate getDateofBirth () {
        return dateOfBirth;
    }

    // for password we dont use getter, we use method to check the user input password instead. 
    // if the it is right return true, if not return false
    // - this.password refer to password inside object class for customer when they do register
    // - inputpassword entered by customer when they login 

    public boolean checkpassword (String inputpassword) {
        return this.password.equals(inputpassword);
    }


    // i use all these setter here for my customer if they want to set their information

    // setter
    // - use when customer want to update their information.

    public void setCustomerName (String customerName) {
        this.customerName = customerName;
    } 

    public void setCustomerAddress (String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerPhone (String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public void setCustomerEmail (String customerEmail) {
        this.customerEmail  = customerEmail;
    }

    public void setDateOfBirth (LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    // - for password of customer if they want to update password 
    // - they need to inter the old password to verify
    // - if right (true) they can update if not they cannot update password
    public boolean updatePassword (String oldPassword , String newPassword) {

        if(checkpassword(oldPassword)) { // verify password
            this.password = newPassword; // update password
            return true; // update sucessefully 
        } else {
            return false; // update unsucessefully
        }
    }
    
        // Single login method: Accepts either email or phone as identifier
        @Override
        public boolean login(String identifier, String password) {
            if ((this.customerEmail.equals(identifier) || this.customerPhone.equals(identifier)) 
                && this.password.equals(password)) {
                return true;
            }
            return false;
        }
    
        @Override
        public boolean register(String customerName, String customerAddress, String customerPhone, String customerEmail, String password, LocalDate dateOfBirth) {
            // Check if customer already exists
            for (Customer customer : customers.values()) {
                if (customer.getCustomerEmail().equals(customerEmail) || customer.getCustomerPhone().equals(customerPhone)) {
                    return false;
                }
            }
               
            // Customer newCustomer = new Customer(customerName, customerAddress, customerPhone, customerEmail, password, dateOfBirth);
            // customers.put (customerEmail, newCustomer);

            return true;
        }

        @Override
        public String toString() {
            return "Name: " + customerName + 
                   "\nPhone: " + customerPhone + 
                   "\nEmail: " + customerEmail + 
                   "\nDateOfBirth: " + dateOfBirth;
        }

        


    }