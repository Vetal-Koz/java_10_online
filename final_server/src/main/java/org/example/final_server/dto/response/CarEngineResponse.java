package org.example.final_server.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.car.CarEngine;


@Getter
@Setter
public class CarEngineResponse extends ApiResponse {

    private String typeOfFuel;

    private String displacement;

    private Integer horsepower;

    private String fuelEconomy;

    private Integer torque;

    private Integer cylinders;

    public CarEngineResponse(CarEngine carEngine) {
        setId(carEngine.getId());
        this.typeOfFuel = carEngine.getTypeOfFuel().name();
        this.displacement = String.valueOf(carEngine.getDisplacement());
        this.horsepower = carEngine.getHorsepower();
        this.fuelEconomy = String.valueOf(carEngine.getFuelEconomy());
        this.torque = carEngine.getTorque();
        this.cylinders = carEngine.getCylinders();
    }

}
