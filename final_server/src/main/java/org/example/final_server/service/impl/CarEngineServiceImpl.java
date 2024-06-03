package org.example.final_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.entity.car.CarEngine;
import org.example.final_server.exception.EntityNotFoundException;
import org.example.final_server.repository.car.CarEngineRepository;
import org.example.final_server.service.CarEngineService;
import org.example.final_server.util.ExceptionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarEngineServiceImpl implements CarEngineService {

    private final CarEngineRepository carEngineRepository;

    @Override
    public void create(CarEngine entity) {
        carEngineRepository.save(entity);
    }

    @Override
    public void update(CarEngine entity) {
        carEngineRepository.save(entity);
    }

    @Override
    public CarEngine findById(Long id) {
        return carEngineRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND.getMessage()));
    }

    @Override
    public void delete(Long id) {
        carEngineRepository.deleteById(id);
    }

    @Override
    public Page<CarEngine> findAll(DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort()
        );
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return carEngineRepository.findAll(pageable);
    }

    @Override
    public List<CarEngine> findAllByIdIn(List<Long> ids) {
        return carEngineRepository.findAllByIdIn(ids);
    }
}
