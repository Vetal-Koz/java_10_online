package org.example.final_server.util;

import lombok.Getter;

@Getter
public enum ValidationUtil {
    EMAIL_REGEX_PATTERN("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$"),
    PASSWORD_REGEX_PATTERN("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$");

    private final String text;
    ValidationUtil(String text){
        this.text = text;
    }
}
