package org.example.service.impl;

import org.example.dto.DataTableRequest;
import org.example.dto.OrderType;
import org.example.entity.Car;
import org.example.entity.Garage;
import org.example.repository.CarRepository;
import org.example.repository.GarageRepository;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private GarageRepository garageRepository;

    @Override
    public Collection<Car> findCarByGarageId(Long garageId) {
        List<Long> longs = List.of(garageId);
        return carRepository.findByGaragesIdIn(longs);
    }

    @Transactional
    @Override
    public void create(Car entity) {
        carRepository.save(entity);
    }

    @Transactional
    @Override
    public void update(Car entity)
    {
        carRepository.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        carRepository.deleteById(id);

    }

    @Override
    public Car findById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new RuntimeException("Such car does not exist");
        }
    }

    @Override
    public Collection<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Collection<Car> findAll(DataTableRequest tableRequest) {
        String column = tableRequest.getColumn();
        OrderType type = tableRequest.getOrderType();
        int page = tableRequest.getPage() - 1;
        int size = tableRequest.getSize();

        Sort sort = null;
        if (type.equals(OrderType.ASC)){
            sort = Sort.by(column).ascending();
        }else {
            sort = Sort.by(column).descending();
        }
        Pageable pageable = PageRequest.of(page, size, sort);

        return carRepository.findAll(pageable).getContent();
    }

    @Transactional
    @Override
    public void attachCarToGarage(Long carId, Long garageId) {
        Car car = carRepository.findById(carId).orElseThrow(()->new RuntimeException("Entity not found"));
        Garage garage = garageRepository.findById(garageId).orElseThrow(()->new RuntimeException("entity not found"));
        garage.getCars().add(car);
        garageRepository.save(garage);
    }

    @Transactional
    @Override
    public void detachCarFromGarage(Long carId, Long garageId) {
        Garage garage = garageRepository.findById(garageId).orElseThrow(()->new RuntimeException("entity not found"));
        garage.getCars().removeIf(car -> car.getId().equals(carId));
        garageRepository.save(garage);
    }
}
