package kr.co.redbrush.webapp.enums;

import lombok.Getter;

public enum AuthenticationExceptionType {
    NONE(0),
    BAD_CREDENTIAL(1),
    CREDENTIALS_EXPIRED(2),
    ACCOUNT_EXPIRED(3),
    LOCKED(4);

    @Getter
    private int type;

    AuthenticationExceptionType(int type) {
        this.type = type;
    }

    public static AuthenticationExceptionType valueOf(int type) {
        switch(type) {
            case 1:
                return BAD_CREDENTIAL;
            case 2:
                return CREDENTIALS_EXPIRED;
            case 3:
                return ACCOUNT_EXPIRED;
            case 4:
                return LOCKED;
        }

        return NONE;
    }
}
