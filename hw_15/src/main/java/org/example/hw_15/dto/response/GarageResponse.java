package org.example.hw_15.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.hw_15.entity.Car;
import org.example.hw_15.entity.Garage;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class GarageResponse extends ApiResponse {
    private String name;
    private long countOfCars;
    private List<String> carBrands = new ArrayList<>();
    private List<String> carYearsOfCreating = new ArrayList<>();

    public GarageResponse() {
    }

    ;

    public GarageResponse(Garage garage) {
        this.setId(garage.getId());
        this.setName(garage.getName());
        this.setCountOfCars(garage.getCars().size());
        for (Car car : garage.getCars()) {
            carBrands.add(car.getBrand());
            carYearsOfCreating.add(String.valueOf(car.getYearOfCreating()));
        }
    }
}
