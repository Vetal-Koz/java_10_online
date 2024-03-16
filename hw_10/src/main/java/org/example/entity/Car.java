package org.example.entity;

public class Car extends BaseEntity {
    public String brand;
    public int yearOfCreating;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYearOfCreating() {
        return yearOfCreating;
    }

    public void setYearOfCreating(int yearOfCreating) {
        this.yearOfCreating = yearOfCreating;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + getId() + '\'' +
                "brand='" + brand + '\'' +
                ", yearOfCreating=" + yearOfCreating +
                '}';
    }
}
