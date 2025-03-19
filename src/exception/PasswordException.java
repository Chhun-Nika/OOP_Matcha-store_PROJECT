package exception;

public class PasswordException extends Exception {
    public PasswordException ( String message) {
        super (message);
    }

    public static void validate (String password , String comfirmPasswprd) throws PasswordException {
        if (!password.equals(comfirmPasswprd)) {
            throw new PasswordException("Error: The password not match !.");
        } 
        if (password.length() < 6 || !password.matches(".*\\d.*")) {
            throw new PasswordException("Error: Password must be at least 6 characters and contain at least one number.");
        }
    }

}
