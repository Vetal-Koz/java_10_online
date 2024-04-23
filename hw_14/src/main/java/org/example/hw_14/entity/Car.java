package org.example.hw_14.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    private String brand;

    private int yearOfCreating;

    @ManyToMany(mappedBy = "cars")
    @ToString.Exclude
    private Set<Garage> garages;

    public Car() {
        this.garages = new HashSet<>();
    }
}
