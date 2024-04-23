package org.example.hw_14.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.hw_14.entity.Car;

@Getter
@Setter
public class CarResponse extends ApiResponse{
    private String brand;
    private int yearOfCreating;

    public CarResponse() {};

    public CarResponse(Car car){
        this.setId(car.getId());
        this.setBrand(car.getBrand());
        this.setYearOfCreating(car.getYearOfCreating());
    }
}
