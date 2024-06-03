package org.example.final_server.util;

import lombok.Getter;

@Getter
public enum ExceptionUtil {
    ENTITY_NOT_FOUND("entity not found");

    private final String message;
    ExceptionUtil(String message){
        this.message = message;
    }
}
