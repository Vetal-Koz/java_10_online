package org.example.final_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.entity.car.Car;
import org.example.final_server.exception.EntityNotFoundException;
import org.example.final_server.repository.car.CarRepository;
import org.example.final_server.service.CarService;
import org.example.final_server.util.ExceptionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public void create(Car entity) {
        carRepository.save(entity);
    }

    @Override
    public void update(Car entity) {
        carRepository.save(entity);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND.getMessage()));
    }

    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Page<Car> findAll(DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());

        Pageable pageable = PageRequest.of(request.getPage()-1, request.getSize(), sort);
        return carRepository.findAll(pageable);
    }
}
