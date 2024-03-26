package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Column(name = "brand")
    public String brand;

    @Column(name = "year_of_creating")
    public int yearOfCreating;

    @ManyToMany(mappedBy = "cars")
    @ToString.Exclude
    Set<Garage> garages;

    public Car() {
        this.garages = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return yearOfCreating == car.yearOfCreating && Objects.equals(brand, car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, yearOfCreating);
    }
}
