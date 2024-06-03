package org.example.final_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.entity.car.CarVariant;
import org.example.final_server.exception.EntityNotFoundException;
import org.example.final_server.repository.car.CarVariantRepository;
import org.example.final_server.service.CarVariantService;
import org.example.final_server.util.ExceptionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarVariantServiceImpl implements CarVariantService {

    private final CarVariantRepository carVariantRepository;

    @Override
    public void create(CarVariant entity) {
        carVariantRepository.save(entity);
    }

    @Override
    public void update(CarVariant entity) {
        carVariantRepository.save(entity);
    }

    @Override
    public CarVariant findById(Long id) {
        return carVariantRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND.getMessage()));
    }

    @Override
    public void delete(Long id) {
        carVariantRepository.deleteById(id);
    }

    @Override
    public Page<CarVariant> findAll(DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort()
        );
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSize(), sort);
        return carVariantRepository.findAll(pageable);
    }

    @Override
    public List<CarVariant> findByCarId(Long carId) {
        return carVariantRepository.findByCarId(carId);
    }
}
