package org.example.dao;

import org.example.entity.Car;

import java.util.Collection;
import java.util.Optional;

public interface CarDao extends CrudDao<Car>{
    void attachCarToGarage(Long carId, Long garageId);
    void detachCarFromGarage(Long carId, Long garageId);
    Collection<Car> findCarByGarageId(Long garageId);
}
