package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
@Entity
@Table(name = "garage")
public class Garage extends BaseEntity {

    private String name;

    @ManyToMany
    @JoinTable(
            name = "gar_car",
            joinColumns = @JoinColumn(name = "gar_id"),
            inverseJoinColumns =   @JoinColumn(name = "car_id")
    )
    @ToString.Exclude
    Set<Car> cars;

    public Garage(){
        this.cars = new HashSet<>();
    }

}
