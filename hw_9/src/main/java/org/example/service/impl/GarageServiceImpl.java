package org.example.service.impl;

import org.example.dao.impl.GarageDao;
import org.example.entity.Car;
import org.example.entity.Garage;
import org.example.service.CrudService;

import java.util.Collection;
import java.util.Optional;

public class GarageServiceImpl implements CrudService<Garage> {
    private final GarageDao garageDao = new GarageDao();

    @Override
    public void create(Garage entity) {
        garageDao.create(entity);
    }

    @Override
    public void update(Garage entity) {
        garageDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        garageDao.delete(id);
    }

    @Override
    public Garage findById(Long id) {
        Optional<Garage> garageOptional = garageDao.findById(id);
        if (garageOptional.isPresent()) {
            return garageOptional.get();
        } else {
            throw new RuntimeException("Garage with such id does not exist");
        }
    }

    @Override
    public Collection<Garage> findAll() {
        if (garageDao.findAll().isEmpty()) {
            throw new RuntimeException("List of garages is empty");
        } else {
            return garageDao.findAll();
        }
    }
}
