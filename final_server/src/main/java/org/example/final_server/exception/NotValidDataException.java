package org.example.final_server.exception;

public class NotValidDataException extends RuntimeException {

    public NotValidDataException(String message) {
        super(message);
    }
}
