package org.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
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
        System.out.println("If you want interact with carGarage please enter 3");
        System.out.println("If you want exit please enter 10");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> new CarController().start(reader);
            case "2" -> new GarageController().start(reader);
            case "3" -> new CarGarageController().start(reader);
            case "10" -> throw new RuntimeException("exit");
        }
    }
}
