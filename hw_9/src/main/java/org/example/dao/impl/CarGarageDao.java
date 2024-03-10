package org.example.dao.impl;

import org.example.db.impl.CarGarageJsonFileDb;
import org.example.entity.CarGarage;

import java.util.Collection;

public class CarGarageDao {

    private final CarGarageJsonFileDb fileDb = new CarGarageJsonFileDb();

    public void create(CarGarage entity) {
        fileDb.create(entity);
    }


    public void delete(Long carId, Long garageId) {
        fileDb.delete(carId, garageId);
    }

    public Collection<CarGarage> findAll() {
        return fileDb.findAll();
    }

}
