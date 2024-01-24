package org.example.controller;

import org.example.Main;
import org.example.entity.Car;
import org.example.entity.CarGarage;
import org.example.entity.Garage;
import org.example.service.CarGarageService;
import org.example.service.CarService;
import org.example.service.GarageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarGarageController {
    private final CarGarageService carGarageService = new CarGarageService();

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
        System.out.println("If you want to see all relations please enter 1");
        System.out.println("If you want to remove relation please enter 2");
        System.out.println("If you want exit please enter exit");
    }

    void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> readAll();
            case "2" -> delete(reader);
            case "exit" -> {
                Main.start();}
        }
    }

    void readAll(){
        for (CarGarage carGarage : carGarageService.findAll()){
            if (carGarage != null) {
                System.out.println(
                        "Car id: " + carGarage.getCarId() +
                                ", Garage id: " + carGarage.getGarageId()
                );
            }
        }
    }

    void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter garage id");
        String garageIdString = reader.readLine();
        if (garageIdString.matches("-?\\d+")) {
            int garageId = Integer.parseInt(garageIdString);
            System.out.println("Please enter car id");
            String carIdString = reader.readLine();
            if (carIdString.matches("-?\\d+")) {
                int carId = Integer.parseInt(carIdString);
                carGarageService.deleteCarGarage(carId, garageId);
            } else {
                System.out.println("Input true format of id");
            }
        }

    }
}