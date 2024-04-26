package org.example.hw_15.service;


import org.example.hw_15.entity.Car;
import org.example.hw_15.entity.Garage;

import java.util.Collection;
import java.util.List;

public interface GarageService extends CrudService<Garage> {
    void attachCarToGarage(Long garageId, Long carId);

    void attachCarsToGarage(Long garageId, List<Long> carIds);

    void detachCarsFromGarage(Long garageId, List<Long> carIds);

    Collection<Car> findByNotAttachToGarage(Long id);

    Collection<Car> findByAttachToGarage(Long id);
}
