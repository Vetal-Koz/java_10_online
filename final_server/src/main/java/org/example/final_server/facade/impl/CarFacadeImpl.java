package org.example.final_server.facade.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.CarRequest;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.dto.response.CarResponse;
import org.example.final_server.dto.response.DataTableResponse;
import org.example.final_server.entity.car.Car;
import org.example.final_server.entity.car.CarVariant;
import org.example.final_server.facade.CarFacade;
import org.example.final_server.service.CarService;
import org.example.final_server.service.CarVariantService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class CarFacadeImpl implements CarFacade {

    private final CarService carService;
    private final CarVariantService carVariantService;

    @Override
    public void create(CarRequest entity) {
        Car car = new Car();
        BeanUtils.copyProperties(entity, car, "maxPrice", "minPrice");
        carService.create(car);
    }

    @Override
    public void update(CarRequest entity, Long id) {
        Car car = carService.findById(id);
        BeanUtils.copyProperties(entity, car, "maxPrice", "minPrice");
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
    public DataTableResponse<CarResponse> findAll(DataTableRequest request) {
        Page<Car> page = carService.findAll(request);
        DataTableResponse<CarResponse> dataTableResponse = new DataTableResponse<>(page);
        dataTableResponse.setSort(request.getSort());
        dataTableResponse.setOrder(request.getOrder());
        List<CarResponse> carResponseList = page.getContent()
                .stream()
                .map(CarResponse::new)
                .peek(carResponse -> {
                    List<CarVariant> carVariantList = carVariantService.findByCarId(carResponse.getId());
                    OptionalDouble minPrice = carVariantList
                            .stream()
                            .map(CarVariant::getPrice)
                            .mapToDouble(BigDecimal::doubleValue)
                            .min();
                    OptionalDouble maxPrice = carVariantList
                            .stream()
                            .map(CarVariant::getPrice)
                            .mapToDouble(BigDecimal::doubleValue)
                            .max();
                    carResponse.setMinPrice(minPrice.isPresent() ? String.valueOf(minPrice.getAsDouble()) : "00.00");
                    carResponse.setMaxPrice(maxPrice.isPresent() ? String.valueOf(maxPrice.getAsDouble()) : "00.00");
                })
                .toList();
        dataTableResponse.setItems(carResponseList);
        return dataTableResponse;
    }
}
