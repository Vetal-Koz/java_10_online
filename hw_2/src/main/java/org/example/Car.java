package org.example;

public class Car {
    public String brand;
    public int yearOfCreating;

    public boolean validateBrand(String brand) {
        if (Character.isUpperCase(brand.charAt(0))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateYearOfCreating(int yearOfCreating) {
        if (yearOfCreating >= 0 && yearOfCreating <= 2024) {
            return true;
        } else {
            return false;
        }
    }
}
