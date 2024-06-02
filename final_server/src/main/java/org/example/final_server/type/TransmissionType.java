package org.example.final_server.type;
import lombok.Getter;

@Getter
public enum TransmissionType {
    MANUAL("manual"),
    AUTOMATIC("automatic");

    private final String type;

    TransmissionType(String type) {
        this.type = type;
    }
}
