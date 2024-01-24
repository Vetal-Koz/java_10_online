package org.example.controller;

import org.example.Main;
import org.example.entity.Car;
import org.example.service.CarService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarController {
    private final CarService carService = new CarService();

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = reader.readLine()) != null) {
            crud(position, reader);
            menu();
        }
    }

    void menu() {
        System.out.println();
        System.out.println("If you want create car please enter 1");
        System.out.println("If you want show all cars please enter 2");
        System.out.println("If you want show car by id please enter 3");
        System.out.println("If you want update car please enter 4");
        System.out.println("If you want delete car please enter 5");
        System.out.println("If you want show cars by garage id please enter 6");
        System.out.println("If you want exit please enter exit");
    }

    void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> update(reader);
            case "5" -> delete(reader);
            case "6" -> readByGarageId(reader);
            case "exit" -> {
                Main.start();
            }
        }
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter brand");
        String brand = reader.readLine();
        while (!validateBrand(brand)) {
            System.out.println("Please enter another brand which start with Upper Char");
            brand = reader.readLine();
        }
        System.out.println("Please enter year of creating");
        String yearOfCreatingString = reader.readLine();
        String correctYear = validateCorrectParse(yearOfCreatingString, reader);
        int yearOfCreating = Integer.parseInt(correctYear);
        Car car = new Car();
        car.setBrand(brand);
        car.setYearOfCreating(yearOfCreating);
        carService.create(car);
    }

    private void readAll() {
        for (Car car : carService.findAll()) {
            if (car != null) {
                System.out.println("Id - " + car.getId() + " Car Brand - " + car.brand + " Year of creating - " + car.yearOfCreating);
            }
        }
    }

    private void readByGarageId(BufferedReader reader) throws IOException {
        System.out.println("Please enter garage id");
        String idString = reader.readLine();
        if (idString.matches("-?\\d+")) {
            int id = Integer.parseInt(idString);
            if (carService.allCarsByGarage(id) != null) {
                for (Car car : carService.allCarsByGarage(id)) {
                    System.out.println(
                            "Brand : " + car.getBrand() +
                                    ", Year of creating: " + car.getYearOfCreating());
                }
            } else {
                System.out.println("There aren't any cars in this garage");
            }
        } else {
            System.out.println("Please enter true format of id");
        }
    }

    void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        if (idString.matches("-?\\d+")) {
            int id = Integer.parseInt(idString);
            Car car = carService.findById(id);
            if (car != null) {
                System.out.println(
                        "Brand : " + car.getBrand() +
                                ", Year of creating: " + car.getYearOfCreating());
            } else {
                System.out.println("car not found");
            }
        } else {
            System.out.println("Please enter true format of id");
        }
    }

    void update(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        if (idString.matches("-?\\d+")) {
            int id = Integer.parseInt(idString);
            Car car = carService.findById(id);
            if (car != null) {
                System.out.println("Please enter brand");
                String brand = reader.readLine();
                while (!validateBrand(brand)) {
                    System.out.println("Please enter another brand which start with Upper Char");
                    brand = reader.readLine();
                }
                System.out.println("Please enter year of creating");
                String yearOfCreatingString = reader.readLine();
                String correctYear = validateCorrectParse(yearOfCreatingString, reader);
                int yearOfCreating = Integer.parseInt(correctYear);
                car = new Car();
                car.setBrand(brand);
                car.setYearOfCreating(yearOfCreating);
                car.setId(id);
                carService.update(car);
            } else {
                System.out.println("car not found");
            }
        } else {
            System.out.println("Please enter true format of id");
        }
    }

    void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        if (idString.matches("-?\\d+")) {
            int id = Integer.parseInt(idString);
            carService.delete(id);
            Car car = carService.findById(id);
            if (car == null) {
                System.out.println("Car was deleted");
            }
        } else {
            System.out.println("Please enter true format of id");
        }
    }

    public boolean validateBrand(String brand) {
        if (Character.isUpperCase(brand.charAt(0))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validateYearOfCreating(int yearOfCreating) {
        if (yearOfCreating >= 0 && yearOfCreating <= 2024) {
            return true;
        } else {
            return false;
        }
    }

    private String validateCorrectParse(String yearOfCreatingString, BufferedReader bufferedReader) throws IOException {
        while (!yearOfCreatingString.matches("[0-9]+")) {
            System.out.println("Please enter correct type of year");
            yearOfCreatingString = bufferedReader.readLine();
        }
        if (!validateYearOfCreating(Integer.parseInt(yearOfCreatingString))) {
            System.out.println("Please enter correct year");
            yearOfCreatingString = bufferedReader.readLine();
            validateCorrectParse(yearOfCreatingString, bufferedReader);
        }
        return yearOfCreatingString;
    }

}
