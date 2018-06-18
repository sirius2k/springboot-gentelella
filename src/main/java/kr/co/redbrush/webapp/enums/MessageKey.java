package kr.co.redbrush.webapp.enums;

import lombok.Getter;

public enum MessageKey {
    // Common
    VALIDATION_FAILED("message.validation.failed"),

    // Signup
    ADMIN_ALREADY_CREATED("message.signup.failed.admin.already.created"),
    ADMIN_CREATE_ERROR("message.signup.failed.admin.create.error");

    @Getter
    private String key;

    MessageKey(String key) {
        this.key = key;
    }
}
