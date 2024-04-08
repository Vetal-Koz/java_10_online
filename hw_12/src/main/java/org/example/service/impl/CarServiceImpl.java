package org.example.service.impl;

import org.example.config.ObjectFactory;
import org.example.dao.CarDao;
import org.example.dao.DataTableRequest;
import org.example.entity.Car;
import org.example.service.CarService;

import java.util.Collection;
import java.util.Optional;

public class CarServiceImpl implements CarService {

    private final CarDao carDao = ObjectFactory.getInstance().getService(CarDao.class);

    @Override
    public Collection<Car> findCarByGarageId(Long garageId) {
        return carDao.findCarByGarageId(garageId);
    }

    @Override
    public void create(Car entity) {
        carDao.create(entity);
    }

    @Override
    public void update(Car entity) {
        carDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        carDao.delete(id);
    }

    @Override
    public Car findById(Long id) {
        Optional<Car> car = carDao.findById(id);
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new RuntimeException("Such car does not exist");
        }
    }

    @Override
    public Collection<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Collection<Car> findAll(DataTableRequest tableRequest) {
        return carDao.findAll(tableRequest);
    }

    @Override
    public void attachCarToGarage(Long carId, Long garageId) {
        carDao.attachCarToGarage(carId, garageId);
    }

    @Override
    public void detachCarFromGarage(Long carId, Long garageId) {
        carDao.detachCarFromGarage(carId, garageId);
    }
}
