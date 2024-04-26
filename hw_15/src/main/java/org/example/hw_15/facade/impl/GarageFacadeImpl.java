package org.example.hw_15.facade.impl;

import lombok.RequiredArgsConstructor;
import org.example.hw_15.dto.DataTableRequest;
import org.example.hw_15.dto.request.GarageRequest;
import org.example.hw_15.dto.response.CarResponse;
import org.example.hw_15.dto.response.GarageResponse;
import org.example.hw_15.entity.Garage;
import org.example.hw_15.facade.GarageFacade;
import org.example.hw_15.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GarageFacadeImpl implements GarageFacade {

    @Autowired
    private final GarageService garageService;

    @Override
    public void create(GarageRequest request) {
        Garage garage = new Garage();
        garage.setName(request.getName());
        garageService.create(garage);
    }

    @Override
    public void update(GarageRequest request, Long id) {
        Garage garage = garageService.findById(id);
        garage.setName(request.getName());
        garageService.update(garage);
    }

    @Override
    public void delete(Long id) {
        garageService.delete(id);
    }

    @Override
    public GarageResponse findById(Long id) {
        return new GarageResponse(garageService.findById(id));
    }

    @Override
    public Collection<GarageResponse> findAll() {
        return garageService.findAll().stream().map(GarageResponse::new).toList();
    }

    @Override
    public Collection<GarageResponse> findAll(DataTableRequest dataTableRequest) {
        return garageService.findAll(dataTableRequest).stream().map(GarageResponse::new).toList();
    }

    @Override
    public void attachCarToDepartment(Long garageId, Long carId) {
        garageService.attachCarToGarage(garageId, carId);
    }

    @Override
    public Collection<CarResponse> findByAttachToGarage(Long id) {
        return garageService.findByAttachToGarage(id).stream().map(CarResponse::new).toList();
    }

    @Override
    public void attachCarsToGarage(Long garageId, List<Long> carIds) {
        garageService.attachCarsToGarage(garageId, carIds);
    }

    @Override
    public List<CarResponse> findByNotAttachToGarage(Long garageId) {
        return garageService.findByNotAttachToGarage(garageId).stream().map(CarResponse::new).toList();
    }

    @Override
    public void detachCarsFromGarage(Long garageId, List<Long> carIds) {
        garageService.detachCarsFromGarage(garageId, carIds);
    }
}
