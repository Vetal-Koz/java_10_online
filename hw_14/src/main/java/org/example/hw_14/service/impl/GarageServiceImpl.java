package org.example.hw_14.service.impl;

import org.example.hw_14.dto.DataTableRequest;
import org.example.hw_14.entity.Car;
import org.example.hw_14.entity.Garage;
import org.example.hw_14.repository.CarRepository;
import org.example.hw_14.repository.GarageRepository;
import org.example.hw_14.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class GarageServiceImpl implements GarageService {

    @Autowired
    private GarageRepository garageRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public void create(Garage entity) {
        garageRepository.save(entity);
    }

    @Override
    public void update(Garage entity) {
        garageRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        garageRepository.deleteById(id);
    }

    @Override
    public Garage findById(Long id) {
        return garageRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity does not exist"));
    }

    @Override
    public Collection<Garage> findAll() {
        return garageRepository.findAll();
    }

    @Override
    public Collection<Garage> findAll(DataTableRequest dataTableRequest) {
        int page = dataTableRequest.getPage() - 1;
        int size = dataTableRequest.getSize();
        Pageable pageable = PageRequest.of(page, size);
        return garageRepository.findAll(pageable).getContent();
    }

    @Override
    public void attachCarToGarage(Long garageId, Long carId) {
        Garage garage = garageRepository.findById(garageId).orElseThrow(() -> new RuntimeException("Entity does not exist"));
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Entity does not exist"));
        Set<Car> attachedCars = garage.getCars();
        attachedCars.add(car);
        garageRepository.save(garage);
    }

    @Override
    public Collection<Car> findCarsByGarageId(Long id) {
        Garage garage = garageRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity does not exist"));
        return garage.getCars();
    }
}
