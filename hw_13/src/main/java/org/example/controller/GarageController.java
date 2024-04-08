package org.example.controller;

import org.example.entity.Garage;
import org.example.service.GarageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

@Service
public class GarageController {

    @Autowired
    private GarageService garageService;

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
        System.out.println("If you want create garage please enter 1");
        System.out.println("If you want show all garages please enter 2");
        System.out.println("If you want show garage by id please enter 3");
        System.out.println("If you want delete by id please enter 4");
        System.out.println("If you want update by id please enter 5");
        System.out.println("If you want exit please enter 10");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> delete(reader);
            case "5" -> update(reader);
            case "10" -> throw new RuntimeException("exit");
        }
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String id = reader.readLine();
        validateId(id);
        System.out.println("Please enter name");
        String name = reader.readLine();
        Garage garage = new Garage();
        garage.setId(Long.valueOf(id));
        garage.setName(name);
        garageService.update(garage);
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String name = reader.readLine();
        Garage garage = new Garage();
        garage.setName(name);
        garageService.create(garage);
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        validateId(idString);
        Garage garage = garageService.findById(Long.parseLong(idString));
        System.out.println("garage = " + garage);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        validateId(idString);
        garageService.delete(Long.parseLong(idString));
    }

    private void readAll() {
        garageService.findAll().forEach(System.out::println);
    }

    private void validateId(String id) {
        if (!id.matches("[0-9]+")) {
            throw new RuntimeException("Недопустиме значення для id");
        }
    }
}
