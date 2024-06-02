package org.example.final_server.entity.car;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.BaseEntity;
import org.example.final_server.type.BodyType;
import org.example.final_server.type.BrandType;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    BrandType brand;

    @Column(nullable = false)
    String model;

    @Column(nullable = false)
    String generation;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type", nullable = false)
    BodyType bodyType;

    @Column(length = 4096)
    String description;

    @ManyToMany
    @JoinTable(
            name = "thumbnails",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns =  @JoinColumn(name = "car_image_id")
    )
    private Set<CarImage> carImages;
    public Car(){
        this.carImages = new HashSet<>();
    }
}
