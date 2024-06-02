package org.example.final_server.entity.car;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.BaseEntity;
import org.example.final_server.type.DrivetrainType;
import org.example.final_server.type.TransmissionType;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "car_variant")
public class CarVariant extends BaseEntity {

    @Column(nullable = false)
    private String color;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer kilometrage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionType transmission;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DrivetrainType drivetrain;


    @Column(nullable = false)
    @Digits(integer = 6, fraction = 2)
    private BigDecimal price;


    @Column(name = "number_of_doors")
    private Integer numberOfDoors;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;


    @Column(name = "safety_rating")
    private Integer safetyRating;

    @ManyToOne
    private Car car;

    @ManyToOne
    private CarEngine carEngine;
}
