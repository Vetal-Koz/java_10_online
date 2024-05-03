package org.example.entity;

public class Garage extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
