package africa.semicolon.passwordManagementSystem.expections;

public class UserNotFoundException extends PasswordManagementException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
