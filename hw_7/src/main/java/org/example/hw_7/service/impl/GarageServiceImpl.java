package org.example.hw_7.service.impl;

import org.example.hw_7.db.DBCarGarage;
import org.example.hw_7.entity.Car;
import org.example.hw_7.entity.Garage;
import org.example.hw_7.exception.EntityNotFoundException;
import org.example.hw_7.service.GarageService;

import java.util.Collection;

public class GarageServiceImpl implements GarageService {
    DBCarGarage instance = DBCarGarage.getInstance();
    @Override
    public void create(Garage entity) {
        instance.createGarage(entity);
    }

    @Override
    public void update(Garage entity) {
        if(entity != null){
            instance.updateGarage(entity);
        }
        else {
            throw new EntityNotFoundException("Garage with such id is not found");
        }
    }

    @Override
    public void delete(Long id) {
        instance.deleteGarage(id);
    }

    @Override
    public Garage findById(Long id) {
        Garage garage = instance.findGarageById(id);
        if (garage != null){
            return garage;
        }
        else {
            throw new EntityNotFoundException("Garage with such id is not found");
        }
    }

    @Override
    public Collection<Garage> findAll() {
        return instance.findAllGarage();
    }
}
