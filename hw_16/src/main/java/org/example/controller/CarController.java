package org.example.controller;

import org.example.entity.Car;
import org.example.service.CarService;

import java.io.BufferedReader;
import java.io.IOException;

public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    public void start(BufferedReader reader) throws IOException {
        menu();
        String position = "";
        while ((position = reader.readLine()) != null) {
            crud(position, reader);
            menu();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create car please enter 1");
        System.out.println("If you want show all cars please enter 2");
        System.out.println("If you want show car by id please enter 3");
        System.out.println("If you want delete by id please enter 4");
        System.out.println("If you want update by id please enter 5");
        System.out.println("If you want to attach car to garage please enter 6");
        System.out.println("If you want to detach car from garage please enter 7");
        System.out.println("If you want to see all cars by garageId please enter 8");
        System.out.println("If you want exit please enter 10");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> delete(reader);
            case "5" -> update(reader);
            case "6" -> attachCarToGarage(reader);
            case "7" -> detachCarFromGarage(reader);
            case "8" -> findAllCarsByGarageId(reader);
            case "10" -> throw new RuntimeException("exit");
        }
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String id = reader.readLine();
        validateId(id);
        System.out.println("Please enter brand");
        String brand = reader.readLine();
        System.out.println("Please enter year of creating");
        String yearOfCreatingString = reader.readLine();
        Car car = new Car();
        car.setId(Long.valueOf(id));
        car.setBrand(brand);
        car.setYearOfCreating(Integer.parseInt(yearOfCreatingString));
        carService.update(car);
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter brand");
        String brand = reader.readLine();
        System.out.println("Please enter year of creating");
        String yearOfCreatingString = reader.readLine();
        int yearOfCreating = Integer.parseInt(yearOfCreatingString);
        Car car = new Car();
        car.setBrand(brand);
        car.setYearOfCreating(yearOfCreating);
        carService.create(car);
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        validateId(idString);
        Car car = carService.findById(Long.parseLong(idString));
        System.out.println("car = " + car);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        validateId(idString);
        carService.delete(Long.parseLong(idString));
    }

    private void readAll() {
        carService.findAll().forEach(System.out::println);
    }

    public void attachCarToGarage(BufferedReader reader) throws IOException {
        System.out.println("Please enter car id");
        String carIdString = reader.readLine();
        validateId(carIdString);
        System.out.println("Please enter garage id");
        String garageIdString = reader.readLine();
        validateId(garageIdString);
        carService.attachCarToGarage(Long.parseLong(carIdString), Long.parseLong(garageIdString));
    }

    public void findAllCarsByGarageId(BufferedReader reader) throws IOException {
        System.out.println("Please enter garage id");
        String garageIdString = reader.readLine();
        validateId(garageIdString);
        carService.findAllCarsByGarage(Long.parseLong(garageIdString)).forEach(System.out::println);
    }

    public void detachCarFromGarage(BufferedReader reader) throws IOException {
        System.out.println("Please enter car id");
        String carIdString = reader.readLine();
        validateId(carIdString);
        System.out.println("Please enter garage id");
        String garageIdString = reader.readLine();
        validateId(garageIdString);
        carService.detachCarFromGarage(Long.parseLong(carIdString), Long.parseLong(garageIdString));
    }

    private void validateId(String id) {
        if (!id.matches("[0-9]+")) {
            throw new RuntimeException("Недопустиме значення для id");
        }
    }

}
