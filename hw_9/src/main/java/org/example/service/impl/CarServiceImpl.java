package org.example.service.impl;

import org.example.dao.impl.CarDao;
import org.example.entity.Car;
import org.example.service.CrudService;

import java.util.Collection;
import java.util.Optional;

public class CarServiceImpl implements CrudService<Car> {

    private final CarDao carDao = new CarDao();
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
        Optional<Car> carOptional = carDao.findById(id);
        if (carOptional.isPresent()){
            return carOptional.get();
        }
        else {
            throw new RuntimeException("Car with such id does not exist");
        }
    }

    @Override
    public Collection<Car> findAll() {
        if (carDao.findAll().isEmpty()){
            throw new RuntimeException("List of cars is empty");
        }else {
            return carDao.findAll();
        }
    }
}
