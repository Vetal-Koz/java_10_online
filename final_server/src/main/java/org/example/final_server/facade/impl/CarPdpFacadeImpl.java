package org.example.final_server.facade.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.response.CarPdpResponse;
import org.example.final_server.entity.car.Car;
import org.example.final_server.entity.car.CarVariant;
import org.example.final_server.facade.CarPdpFacade;
import org.example.final_server.service.CarService;
import org.example.final_server.service.CarVariantService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CarPdpFacadeImpl implements CarPdpFacade {

    private final CarService carService;
    private final CarVariantService carVariantService;

    @Override
    public CarPdpResponse findByCar(Long carId) {
        Car car = carService.findById(carId);
        List<CarVariant> carVariantList = carVariantService.findByCarId(carId);
        return new CarPdpResponse(car, carVariantList);
    }
}
