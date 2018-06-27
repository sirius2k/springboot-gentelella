package kr.co.redbrush.webapp.exception;

public class AdminRoleNotFoundException extends RuntimeException {
    public AdminRoleNotFoundException(String message) {
        super(message);
    }
}
