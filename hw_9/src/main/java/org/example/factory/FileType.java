package org.example.factory;

public enum FileType {

    CARS_JSON("cars.json"),
    GARAGES_JSON("garages.json"),
    CAR_GARAGES_JSON("carGarages.json"),
    STUDENTS_CSV("students.csv");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
