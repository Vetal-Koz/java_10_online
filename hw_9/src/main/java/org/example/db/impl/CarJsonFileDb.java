package org.example.db.impl;

import com.google.gson.Gson;
import org.example.db.FileDb;
import org.example.entity.Car;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CarJsonFileDb implements FileDb<Car> {

    private final List<Car> cars = new ArrayList<>();
    private Long carId = 0L;

    @Override
    public void create(Car entity) {
        readJson();
        entity.setId(generateId());
        cars.add(entity);
        writeJson();
    }

    @Override
    public void update(Car entity) {
        readJson();
        Optional<Car> oldCarOptional = findById(entity.getId());
        if (oldCarOptional.isPresent()) {
            Car oldCar = oldCarOptional.get();
            oldCar.setBrand(entity.getBrand());
            oldCar.setYearOfCreating(entity.getYearOfCreating());
        }
        writeJson();
    }

    @Override
    public void delete(Long id) {
        readJson();
        Optional<Car> deleteCarOptional = findById(id);
        if (deleteCarOptional.isPresent()) {
            Car deleteCar = deleteCarOptional.get();
            cars.remove(deleteCar);
        }
        CarGarageJsonFileDb carGarageJsonFileDb = new CarGarageJsonFileDb();
        carGarageJsonFileDb.deleteAllByCarId(id);
        writeJson();
    }

    @Override
    public Optional<Car> findById(Long id) {
        return cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Car> findAll() {
        readJson();
        return cars;
    }

    private void readJson() {
        Gson gson = new Gson();
        try {
            Car[] from = gson.fromJson(new FileReader("cars.json"), Car[].class);
            if (from != null) {
                this.cars.clear();
                this.cars.addAll(List.of(from));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("cars.json")) {
            String json = gson.toJson(this.cars);
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long generateId() {
        readJson();
        if (!cars.isEmpty()) {
            carId = cars.getLast().getId();
        }
        return carId = carId + 1;
    }
}
