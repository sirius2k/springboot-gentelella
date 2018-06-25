package kr.co.redbrush.webapp.enums;

import lombok.Getter;

public enum Role {
    // ADMIN
    ROLE_ADMIN("ROLE_ADMIN");

    @Getter
    private String name;

    Role(String name) {
        this.name = name;
    }
}
