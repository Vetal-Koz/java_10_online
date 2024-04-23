package org.example.hw_14.facade.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_14.dto.DataTableRequest;
import org.example.hw_14.dto.request.CarRequest;
import org.example.hw_14.dto.response.CarResponse;
import org.example.hw_14.entity.Car;
import org.example.hw_14.facade.CarFacade;
import org.example.hw_14.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CarFacadeImpl implements CarFacade {

    @Autowired
    private final CarService carService;

    @Override
    public void create(CarRequest request) {
        Car car = new Car();
        car.setBrand(request.getBrand());
        car.setYearOfCreating(request.getYearOfCreating());
        carService.create(car);
    }

    @Override
    public void update(CarRequest request, Long id) {
        Car car = carService.findById(id);
        car.setBrand(request.getBrand());
        car.setYearOfCreating(request.getYearOfCreating());
        carService.update(car);
    }

    @Override
    public void delete(Long id) {
        carService.delete(id);
    }

    @Override
    public CarResponse findById(Long id) {
        return new CarResponse(carService.findById(id));
    }

    @Override
    public Collection<CarResponse> findAll() {
        return carService.findAll().stream().map(CarResponse::new).toList();
    }

    @Override
    public Collection<CarResponse> findAll(DataTableRequest dataTableRequest) {
        return carService.findAll(dataTableRequest).stream().map(CarResponse::new).toList();
    }
}
