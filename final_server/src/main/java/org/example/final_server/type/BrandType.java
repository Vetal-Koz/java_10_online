package org.example.final_server.type;

import lombok.Getter;

@Getter
public enum BrandType {
    AUDI("Audi"),
    MERCEDES("Mercedes-Benz"),
    TOYOTA("Toyota"),
    FORD("Ford"),
    HONDA("Honda"),
    CHEVROLET("Chevrolet"),
    NISSAN("Nissan"),
    VOLKSWAGEN("Volkswagen"),
    HYUNDAI("Hyundai"),
    KIA("Kia"),
    LEXUS("Lexus"),
    MAZDA("Mazda"),
    SUBARU("Subaru"),
    VOLVO("Volvo"),
    JAGUAR("Jaguar"),
    LAND_ROVER("Land Rover"),
    PORSCHE("Porsche"),
    FERRARI("Ferrari"),
    LAMBORGHINI("Lamborghini"),
    ASTON_MARTIN("Aston Martin"),
    MASERATI("Maserati"),
    BENTLEY("Bentley"),
    ROLLS_ROYCE("Rolls-Royce"),
    BUGATTI("Bugatti"),
    ALFA_ROMEO("Alfa Romeo"),
    PEUGEOT("Peugeot"),
    RENAULT("Renault");

    private final String brand;

    BrandType(String brand) {
        this.brand = brand;
    }
}
