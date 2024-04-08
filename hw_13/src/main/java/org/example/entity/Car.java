package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "brand")
    public String brand;

    public int yearOfCreating;

    @ManyToMany(mappedBy = "cars")
    @ToString.Exclude
    Set<Garage> garages;

    public Car(){
        this.garages = new HashSet<>();
    }

}
