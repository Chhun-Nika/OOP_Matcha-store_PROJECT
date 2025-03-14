package exception;

public class PositiveIntegerException extends NumberFormatException {
    public PositiveIntegerException (String message) {
        super(message);
    }

    public static void validate (String input) {
        if (!input.matches("^\\d+$") || Double.parseDouble(input) <= 0) {
            throw new PositiveIntegerException("Invalid Input! Please enter an positive integer number.");
        }
    }
}
