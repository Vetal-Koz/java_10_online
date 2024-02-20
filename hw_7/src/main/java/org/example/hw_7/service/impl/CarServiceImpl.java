package org.example.hw_7.service.impl;

import org.example.hw_7.db.DBCarGarage;
import org.example.hw_7.entity.Car;
import org.example.hw_7.exception.EntityNotFoundException;
import org.example.hw_7.service.CarService;

import java.util.Collection;

public class CarServiceImpl implements CarService {
    DBCarGarage instance = DBCarGarage.getInstance();
    @Override
    public void create(Car entity) {
        instance.createCar(entity);
    }

    @Override
    public void update(Car entity) {
        if(entity != null){
            instance.updateCar(entity);
        }
        else {
            throw new EntityNotFoundException("Car with such id is not found");
        }
    }

    @Override
    public void delete(Long id) {
       instance.deleteCar(id);
    }

    @Override
    public Car findById(Long id) {
        Car car = instance.findCarById(id);
        if (car != null){
            return car;
        }
        else {
            throw new EntityNotFoundException("Car with such id is not found");
        }
    }

    @Override
    public Collection<Car> findAll() {
        return instance.findAllCars();
    }
}
