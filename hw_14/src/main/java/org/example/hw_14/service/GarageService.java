package org.example.hw_14.service;

import org.example.hw_14.entity.Car;
import org.example.hw_14.entity.Garage;

import java.util.Collection;

public interface GarageService extends CrudService<Garage> {
    void attachCarToGarage(Long garageId, Long carId);

    Collection<Car> findCarsByGarageId(Long id);
}
