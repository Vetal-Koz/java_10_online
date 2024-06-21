package org.example.final_server.util;

import lombok.Getter;

@Getter
public enum ExceptionUtil {
    ENTITY_NOT_FOUND("entity not found"),
    USER_ALREADY_EXIST("such user already exists"),
    EMAIL_IS_NOT_PRESENT("email is not present"),
    EMAIL_IS_NOT_VALID("email is not valid"),
    EMAIL_IS_EXIST("user with such email exists"),
    PASSWORD_IS_NOT_PRESENT("password is not present"),
    PASSWORD_IS_NOT_VALID("password is not valid");

    private final String message;

    ExceptionUtil(String message) {
        this.message = message;
    }
}
