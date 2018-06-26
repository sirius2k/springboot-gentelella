package kr.co.redbrush.webapp.enums;

import lombok.Getter;

public enum MessageKey {
    // Common
    VALIDATION_FAILED("message.validation.failed"),

    // Signup
    ADMIN_ALREADY_CREATED("message.signup.failed.admin.already.created"),
    ADMIN_CREATE_ERROR("message.signup.failed.admin.create.error"),
    ADMIN_ROLE_NOT_FOUND("message.signup.admin.role.not.found"),
    PASSWORD_EMPTY("message.signup.password.empty");

    @Getter
    private String key;

    MessageKey(String key) {
        this.key = key;
    }
}
