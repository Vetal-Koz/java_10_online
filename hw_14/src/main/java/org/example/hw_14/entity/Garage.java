package org.example.hw_14.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "garages")
public class Garage extends BaseEntity{

    private String name;

    @ManyToMany
    @JoinTable(
            name = "gar_car",
            joinColumns = @JoinColumn(name = "gar_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    @ToString.Exclude
    private Set<Car> cars;

    public Garage(){
        this.cars = new HashSet<>();
    }
}
