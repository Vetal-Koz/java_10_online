package org.example.final_server.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.entity.car.CarImage;
import org.example.final_server.exception.EntityNotFoundException;
import org.example.final_server.repository.car.CarImageRepository;
import org.example.final_server.service.CarImageService;
import org.example.final_server.util.ExceptionUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarImageServiceImpl implements CarImageService {

    private final CarImageRepository carImageRepository;
    @Override
    public void create(CarImage entity) {
        carImageRepository.save(entity);
    }

    @Override
    public void update(CarImage entity) {
        carImageRepository.save(entity);
    }

    @Override
    public CarImage findById(Long id) {
        return carImageRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionUtil.ENTITY_NOT_FOUND.getMessage()));
    }

    @Override
    public void delete(Long id) {
        carImageRepository.deleteById(id);
    }

    @Override
    public Page<CarImage> findAll(DataTableRequest request) {
        Sort sort = Sort.by(
                request.getOrder().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC,
                request.getSort());
        Pageable pageable = PageRequest.of(request.getPage() -1 , request.getSize(), sort);
        return carImageRepository.findAll(pageable);
    }
}
