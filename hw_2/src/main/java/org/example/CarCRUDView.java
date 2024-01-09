package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CarCRUDView {
    Car[] cars = new Car[10];
    int numberOfCars = 0;

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = bufferedReader.readLine()) != null) {
            crud(position, bufferedReader);
            menu();
        }
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> create(bufferedReader);
            case "2" -> readAll();
            case "3" -> System.exit(0);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want to create car please enter 1");
        System.out.println("If you want to show all cars please enter 2");
        System.out.println("If you want to exit please enter 3");
    }

    private void readAll(){
        for (Car car : cars){
            if(car != null){
                System.out.println("Car Brand - " + car.brand + " Year of creating - " + car.yearOfCreating);
            }
        }
    }

    private String validateCorrectParse(String yearOfCreatingString, BufferedReader bufferedReader, Car car) throws IOException {
        while (!yearOfCreatingString.matches("[0-9]+")){
            System.out.println("Please enter correct type of year");
            yearOfCreatingString = bufferedReader.readLine();
        }
        if (!car.validateYearOfCreating(Integer.parseInt(yearOfCreatingString))){
            System.out.println("Please enter correct year");
            yearOfCreatingString = bufferedReader.readLine();
            validateCorrectParse(yearOfCreatingString, bufferedReader, car);
        }
        return yearOfCreatingString;
    }

    private void create(BufferedReader bufferedReader) throws IOException {
        if (numberOfCars == cars.length-1){
            copyAndCreateBiggerArray();
        }
        Car car = new Car();
        System.out.println("Please enter brand");
        String brand = bufferedReader.readLine();
        while (!car.validateBrand(brand)){
            System.out.println("Please enter another brand which start with Upper Char");
            brand = bufferedReader.readLine();
        }
        System.out.println("Please enter year of creating");
        String yearOfCreatingString = bufferedReader.readLine();
        String correctYear = validateCorrectParse(yearOfCreatingString, bufferedReader, car);

        int yearOfCreating = Integer.parseInt(correctYear);
        car.brand = brand;
        car.yearOfCreating = yearOfCreating;
        cars[numberOfCars] = car;
        numberOfCars += 1;
    }

    private void copyAndCreateBiggerArray(){
        Car[] newCars = new Car[cars.length+10];
        for (int i = 0; i<cars.length; i++){
            newCars[i] = cars[i];
        }
        cars = newCars;
    }
    
    


}
