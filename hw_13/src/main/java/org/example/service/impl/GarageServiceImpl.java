package org.example.service.impl;

import org.example.dto.DataTableRequest;
import org.example.entity.Garage;
import org.example.repository.GarageRepository;
import org.example.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service

public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Transactional
    @Override
    public void create(Garage entity) {
        garageRepository.save(entity);
    }

    @Transactional
    @Override
    public void update(Garage entity) {
        garageRepository.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        garageRepository.deleteById(id);
    }

    @Override
    public Garage findById(Long id) {
        Optional<Garage> garage = garageRepository.findById(id);
        if (garage.isPresent()) {
            return garage.get();
        } else {
            throw new RuntimeException("Such garage does not exist");
        }
    }

    @Override
    public Collection<Garage> findAll(DataTableRequest tableRequest) {
        return null;
    }

    @Override
    public Collection<Garage> findAll() {
        return garageRepository.findAll();
    }
}
