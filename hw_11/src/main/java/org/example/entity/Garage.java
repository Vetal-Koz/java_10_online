package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "garages")
public class Garage extends BaseEntity {

    @Column(name = "garage_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "car_garages",
            joinColumns = @JoinColumn(name = "garage_id"),
            inverseJoinColumns = @JoinColumn(name = "emp_id")
    )
    @ToString.Exclude
    private Set<Car> cars;

    public Garage() {
        this.cars = new HashSet<>();
    }
}
