package org.example.hw_7.db;

import org.example.hw_7.entity.Car;
import org.example.hw_7.entity.Garage;

import java.util.ArrayList;
import java.util.List;

public class DBCarGarage {
    private static final DBCarGarage instance = new DBCarGarage();

    private Long carId = 0L;
    private Long garageId = 0L;

    private DBCarGarage() {
    }

    List<Car> cars = new ArrayList<>();
    List<Garage> garages = new ArrayList<>();

    public static DBCarGarage getInstance() {
        return instance;
    }

    public void createCar(Car car) {
        car.setId(generateIdForCar());
        this.cars.add(car);
    }

    public void createGarage(Garage garage) {
        garage.setId(generateIdForGarage());
        this.garages.add(garage);
    }

    public void updateCar(Car newCar) {
        Car car = cars.stream()
                .filter(c -> {
                    return c.getId().equals(newCar.getId());
                })
                .findFirst()
                .get();
        car.setNameOfMark(newCar.getNameOfMark());
        car.setYearOfCreating(newCar.getYearOfCreating());
    }

    public void updateGarage(Garage newGarage) {
        for (Garage garage : garages) {
            if (garage.getId().equals(newGarage.getId())) {
                garage.setName(newGarage.getName());
            }
        }
    }

    public void deleteGarage(Long id) {
        garages.removeIf(garage -> garage.getId().equals(id));
    }

    public void deleteCar(Long id) {
        cars.removeIf(car -> car.getId().equals(id));
    }

    public Car findCarById(Long id) {
        return cars.stream()
                .filter(car -> {
                    return car.getId().equals(id);
                })
                .findFirst()
                .get();
    }

    public Garage findGarageById(Long id) {
        return garages.stream()
                .filter(garage -> {
                    return garage.getId().equals(id);
                })
                .findFirst()
                .get();
    }

    public List<Car> findAllCars() {
        return cars;
    }

    public List<Garage> findAllGarage() {
        return garages;
    }

    private Long generateIdForCar() {
        return this.carId++;
    }

    private Long generateIdForGarage() {
        return this.garageId++;
    }
}
