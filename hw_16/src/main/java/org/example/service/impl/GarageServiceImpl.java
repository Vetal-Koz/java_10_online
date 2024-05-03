package org.example.service.impl;

import org.example.dao.GarageDao;
import org.example.entity.Garage;
import org.example.service.GarageService;

import java.util.Collection;
import java.util.Optional;

public class GarageServiceImpl implements GarageService {

    private final GarageDao garageDao;

    public GarageServiceImpl(GarageDao garageDao) {
        this.garageDao = garageDao;
    }

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
        Optional<Garage> garage = garageDao.findById(id);
        if (garage.isPresent()) {
            return garage.get();
        } else {
            throw new RuntimeException("Such garage does not exist");
        }
    }

    @Override
    public Collection<Garage> findAll() {
        return garageDao.findAll();
    }
}
