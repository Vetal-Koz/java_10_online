package org.example.service;

import org.example.entity.Car;

import java.util.Collection;

public interface CarService extends CrudService<Car> {
    void attachCarToGarage(Long carId, Long garageId);

    void detachCarFromGarage(Long carId, Long garageId);

    Collection<Car> findAllCarsByGarage(Long garageId);
}
