package exception;

public class PositiveDecimalException extends NumberFormatException {
    public PositiveDecimalException (String message) {
        super(message);
    }

    public static void validate (String input) {
        if (!input.matches("^\\d*\\.?\\d+$") || Double.parseDouble(input) <= 0) { 
            if (Double.parseDouble(input) <= 0) {
                throw new PositiveDecimalException("Invalid input! Please enter a decimal number that is bigger than 0");
            } else {
                throw new PositiveDecimalException("Invalid input! Please enter a positive decimal number.");

            }
        }
    }

}
