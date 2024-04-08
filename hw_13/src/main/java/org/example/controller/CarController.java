package org.example.controller;


import org.example.dto.DataTableRequest;
import org.example.dto.OrderType;
import org.example.entity.Car;
import org.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.IOException;

@Service
public class CarController {

    @Autowired
    private CarService carService;

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
            case "2" -> readAll(reader);
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

    private void readAll(BufferedReader reader) throws IOException {
        DataTableRequest tableRequest = new DataTableRequest();
        System.out.println("if you want sort by brand enter 1");
        System.out.println("if you want sort by year of creating enter 2");
        String fieldNumber = reader.readLine();
        switch (fieldNumber) {
            case ("1") -> tableRequest.setColumn("brand");
            case ("2") -> tableRequest.setColumn("yearOfCreating");
        }
        System.out.println("Please choose type of sorting");
        System.out.println("if you want sort ASC enter 1");
        System.out.println("if you want sort DESC enter 2");
        String typeOfSort = reader.readLine();
        switch (typeOfSort) {
            case ("1") -> tableRequest.setOrderType(OrderType.ASC);
            case ("2") -> tableRequest.setOrderType(OrderType.DESC);
        }
        System.out.println("Please enter reviewed page number (from 1 to ...)");
        int page = Integer.parseInt(reader.readLine());
        System.out.println("Please enter reviewed page size (from 1 to ...)");
        int size = Integer.parseInt(reader.readLine());


        tableRequest.setPage(page);
        tableRequest.setSize(size);
        carService.findAll(tableRequest).forEach(System.out::println);


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
        carService.findCarByGarageId(Long.parseLong(garageIdString)).forEach(System.out::println);
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
