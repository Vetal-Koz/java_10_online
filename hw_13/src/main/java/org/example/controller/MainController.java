package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class MainController {

    @Autowired
    @Qualifier("carController")
    private CarController carController;

    @Autowired
    private GarageController garageController;
    public void start() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            menu();
            String position = "";
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                menu();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void menu() {
        System.out.println();
        System.out.println("If you want interact with car please enter 1");
        System.out.println("If you want interact with garage please enter 2");
        System.out.println("If you want exit please enter 10");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> this.carController.start(reader);
            case "2" -> this.garageController.start(reader);
            case "10" -> throw new RuntimeException("exit");
        }
    }
}
