package org.example.final_server.entity.car;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.BaseEntity;
import org.example.final_server.type.FuelType;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "car_engine")
public class CarEngine extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type_of_fuel",nullable = false)
    private FuelType typeOfFuel;

    @Column()
    @Digits(integer = 3, fraction = 2)
    private BigDecimal displacement;

    @Column(nullable = false)
    private Integer horsepower;

    @Column(name = "fuel_economy")
    @Digits(integer = 2, fraction = 1)
    private BigDecimal fuelEconomy;

    @Column(nullable = false)
    private Integer torque;

    @Column()
    private Integer cylinders;



}
