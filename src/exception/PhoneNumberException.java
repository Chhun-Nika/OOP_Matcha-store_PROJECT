package exception;

public class PhoneNumberException extends NumberFormatException {
    public PhoneNumberException(String message) {
        super(message);
    }

    public static void validate(String phone) throws PhoneNumberException {
        if (!phone.matches("\\d{7,10}")) {
            throw new PhoneNumberException("Invalid phone number! It must contain 10 digits.");
        }
    }
}
