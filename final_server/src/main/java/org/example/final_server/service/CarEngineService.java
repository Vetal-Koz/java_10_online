package org.example.final_server.service;

import org.example.final_server.entity.car.CarEngine;

import java.util.List;

public interface CarEngineService extends CrudService<CarEngine>{
    List<CarEngine> findAllByIdIn(List<Long> ids);
}
