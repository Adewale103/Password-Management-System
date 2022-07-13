package africa.semicolon.passwordManagementSystem.expections;

public class InvalidDetailsException extends PasswordManagementException{
    public InvalidDetailsException(String message) {
        super(message);
    }
}
