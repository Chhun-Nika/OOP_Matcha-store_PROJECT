package exception;

public class StringOnlyException extends IllegalArgumentException {
    public StringOnlyException (String message) {
        super(message);
    }

    public static void validate(String input) {
        // Allows only letters and spaces
        if (!input.matches("^[a-zA-Z ]+$")) { 
            throw new StringOnlyException("Invalid input! Only letters are allowed.");
        }
    }
}
