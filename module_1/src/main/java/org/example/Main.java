package org.example;


import org.example.controller.CarController;
import org.example.controller.CarGarageController;
import org.example.controller.GarageController;
import org.example.entity.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = reader.readLine()) != null) {
            crud(position, reader);
            menu();
        }
    }

    static void menu() {
        System.out.println();
        System.out.println("If you want to deal with cars please enter 1");
        System.out.println("If you want to deal with garages please enter 2");
        System.out.println("If you want to deal with carGarage relations please enter 3");
        System.out.println("If you want exit please enter exit");
    }

    static void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> dealWithCar();
            case "2" -> dealWithGarage();
            case "3" -> dealWithRelation();
            case "exit" -> System.exit(0);
        }
    }

    static void dealWithCar() throws IOException {
        CarController carController = new CarController();
        carController.start();
    }

    static void dealWithGarage() throws IOException {
        GarageController garageController = new GarageController();
        garageController.start();
    }

    static void dealWithRelation() throws IOException {
        CarGarageController carGarageController = new CarGarageController();
        carGarageController.start();
    }

}