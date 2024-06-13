package org.example.final_server.type;

import lombok.Getter;

@Getter
public enum FuelType {
    PETROL("petrol"),
    DIESEL("diesel"),
    GAS("gas"),
    ELECTRIC("electric"),
    HYBRID("hybrid");

    private final String type;

    FuelType(String type) {
        this.type = type;
    }
}
