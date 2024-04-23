package org.example.hw_14.service.impl;


import org.example.hw_14.dto.DataTableRequest;
import org.example.hw_14.entity.Car;
import org.example.hw_14.repository.CarRepository;
import org.example.hw_14.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void create(Car entity) {
        carRepository.save(entity);
    }

    @Override
    public void update(Car entity) {
        carRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Entity does not exist"));
    }

    @Override
    public Collection<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Collection<Car> findAll(DataTableRequest dataTableRequest) {
        int page = dataTableRequest.getPage() - 1;
        int size = dataTableRequest.getSize();
        Pageable pageable = PageRequest.of(page, size);

        return carRepository.findAll(pageable).getContent();
    }
}
