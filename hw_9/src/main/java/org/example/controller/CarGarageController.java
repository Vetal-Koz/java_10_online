package org.example.controller;

import org.example.entity.CarGarage;
import org.example.service.impl.CarGarageServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;

public class CarGarageController {
    private final CarGarageServiceImpl carGarageService = new CarGarageServiceImpl();

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
        System.out.println("If you want to attach car to garage please enter 1");
        System.out.println("If you want show all carGarages please enter 2");
        System.out.println("If you want to detach car from garage please enter 3");
        System.out.println("If you want exit please enter 10");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> delete(reader);
            case "10" -> throw new RuntimeException("exit");
        }
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter car id");
        String carId = reader.readLine();
        validateId(carId);
        System.out.println("Please enter garage id");
        String garageId = reader.readLine();
        validateId(garageId);
        CarGarage carGarage = new CarGarage();
        carGarage.setCarId(Long.valueOf(carId));
        carGarage.setGarageId(Long.valueOf(garageId));
        carGarageService.create(carGarage);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter car id");
        String carIdString = reader.readLine();
        validateId(carIdString);
        Long carId = Long.valueOf(carIdString);
        System.out.println("Please enter garage id");
        String garageIdString = reader.readLine();
        validateId(garageIdString);
        Long garageId = Long.valueOf(garageIdString);

        carGarageService.delete(carId, garageId);
    }

    private void readAll() {
        carGarageService.findAll().forEach(System.out::println);
    }

    private void validateId(String id) {
        if (!id.matches("[0-9]+")) {
            throw new RuntimeException("Недопустиме значення для id");
        }
    }

}
