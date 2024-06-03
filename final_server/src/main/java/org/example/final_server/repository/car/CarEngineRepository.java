package org.example.final_server.repository.car;

import org.example.final_server.entity.car.CarEngine;
import org.example.final_server.repository.BaseRepository;

import java.util.List;

public interface CarEngineRepository extends BaseRepository<CarEngine> {
    
    List<CarEngine> findAllByIdIn(List<Long> ids);
}
