package kr.co.redbrush.webapp.exception;

public class PasswordEmptyException extends RuntimeException {
    public PasswordEmptyException(String message) {
        super(message);
    }
}
