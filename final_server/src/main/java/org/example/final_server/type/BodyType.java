package org.example.final_server.type;

import lombok.Getter;

@Getter
public enum BodyType {
    HATCHBACK("hatchback"),
    SEDAN("sedan"),
    COUPE("coupe"),
    WAGON("wagon"),
    CROSSOVER("crossover"),
    SUV("suv");
    private final String name;

    BodyType(String name) {
        this.name = name;
    }
}
