package org.example.controller;

import org.example.Main;
import org.example.entity.Car;
import org.example.entity.CarGarage;
import org.example.entity.Garage;
import org.example.service.CarService;
import org.example.service.GarageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GarageController {
    private final GarageService garageService = new GarageService();
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
        System.out.println("If you want create garage please enter 1");
        System.out.println("If you want show all garages please enter 2");
        System.out.println("If you want show garage by id please enter 3");
        System.out.println("If you want update garage please enter 4");
        System.out.println("If you want delete garage please enter 5");
        System.out.println("If you want attach car to garage please enter 6");
        System.out.println("If you want exit please enter exit");
    }

    void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> update(reader);
            case "5" -> delete(reader);
            case "6" -> attachCar(reader);
            case "exit" -> {
                Main.start();}
        }
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String name = reader.readLine();
        Garage garage = new Garage();
        garage.setName(name);
        garageService.create(garage);
    }

    private void readAll(){
        for (Garage garage : garageService.findAll()){
            if(garage != null){
                System.out.println("Id - "+garage.getId() +" Garage name - " + garage.getName());
            }
        }
    }

    void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        if (idString.matches("-?\\d+")) {
            int id = Integer.parseInt(idString);
            Garage garage = garageService.findById(id);
            if (garage != null) {
                System.out.println(
                        "Name : " + garage.getName()
                                );
            } else {
                System.out.println("garage not found");
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
            Garage garage = garageService.findById(id);
            if (garage != null) {
                System.out.println("Please enter name");
                String name = reader.readLine();
                garage = new Garage();
                garage.setName(name);
                garage.setId(id);
                garageService.update(garage);
            } else {
                System.out.println("Garage not found");
            }
        }    else {
            System.out.println("Please enter true format of id");
        }
    }

    void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        if (idString.matches("-?\\d+")) {
            int id = Integer.parseInt(idString);
            garageService.delete(id);
            Garage garage = garageService.findById(id);
            if (garage == null) {
                System.out.println("Garage was deleted");
            }
        }
        else {
            System.out.println("Please enter true format of id");
        }
    }

    void attachCar(BufferedReader reader) throws IOException{
        System.out.println("Please enter garage id");
        String garageIdString = reader.readLine();
        if (garageIdString.matches("-?\\d+")) {
            int garageId = Integer.parseInt(garageIdString);
            Garage garage = garageService.findById(garageId);
            if (garage != null){
                System.out.println("Please enter car id");
                String carIdString = reader.readLine();
                if (carIdString.matches("-?\\d+")){
                    int carId = Integer.parseInt(carIdString);
                    Car car = carService.findById(carId);
                    if(car != null){
                        garageService.attachCar(carId, garageId);
                    }else{
                        System.out.println("Car not found");
                    }
                }else{
                    System.out.println("Input true format of id");
                }
            }else{
                System.out.println("Garage not found");
            }
        }else{
            System.out.println("Input true format of id");
        }
    }
}
