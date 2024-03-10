package org.example.service.impl;

import org.example.dao.impl.CarGarageDao;
import org.example.entity.CarGarage;

import java.util.Collection;

public class CarGarageServiceImpl {
    private final CarGarageDao carGarageDao = new CarGarageDao();

    public void create(CarGarage entity) {
        carGarageDao.create(entity);
    }

    public void delete(Long carId, Long garageId) {
        carGarageDao.delete(carId, garageId);
    }

    public Collection<CarGarage> findAll() {
        if (carGarageDao.findAll().isEmpty()) {
            throw new RuntimeException("List of carGarages is empty");
        } else {
            return carGarageDao.findAll();
        }
    }
}
