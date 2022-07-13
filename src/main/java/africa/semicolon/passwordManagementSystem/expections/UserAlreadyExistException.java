package africa.semicolon.passwordManagementSystem.expections;

public class UserAlreadyExistException extends  PasswordManagementException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
