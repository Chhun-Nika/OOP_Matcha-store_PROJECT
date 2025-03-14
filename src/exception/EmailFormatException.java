package exception;

public class EmailFormatException extends IllegalArgumentException {
    public EmailFormatException(String message) {
        super(message);
    }

    public static void validate(String email) throws EmailFormatException {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
            throw new EmailFormatException("Invalid email format! Example: name@gmail.com");
        }
    }
}
