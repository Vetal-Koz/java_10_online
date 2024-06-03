package org.example.final_server.service;

import org.example.final_server.entity.car.CarVariant;

import java.util.List;

public interface CarVariantService extends CrudService<CarVariant>{
    List<CarVariant> findByCarId(Long carId);
}
