package org.example.db;

import org.example.entity.Car;
import org.example.entity.CarGarage;
import org.example.entity.Garage;

public final class CarGarageRelation {

    private static CarGarageRelation instance;
    private Car[] cars = new Car[10];
    private Garage[] garages = new Garage[10];
    private CarGarage[] carGarages = new CarGarage[10];

    private Integer countOfCar = 0;
    private Integer carId = 0;
    private Integer countOfGarage = 0;
    private Integer garageId = 0;
    private Integer countOfCarGarages = 0;

    private CarGarageRelation() {
    }

    public static CarGarageRelation getInstance() {
        if (instance == null) {
            instance = new CarGarageRelation();
        }
        return instance;
    }

    public void createCar(Car car) {
        countOfCar += 1;
        if (countOfCar == cars.length) {
            updateSizeOfCarArray();
        }
        car.setId(generateIdForCar());
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                cars[i] = car;
                break;
            }
        }
    }

    public void updateCar(Car car) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null && cars[i].getId().equals(car.getId())) {
                cars[i] = car;
                break;
            }
        }
    }

    public void createGarage(Garage garage) {
        countOfGarage += 1;
        if (countOfGarage == garages.length) {
            updateSizeOfGarageArray();
        }
        garage.setId(generateIdForGarage());
        for (int i = 0; i < garages.length; i++) {
            if (garages[i] == null) {
                garages[i] = garage;
                break;
            }
        }
    }

    public void updateGarage(Garage garage) {
        for (int i = 0; i < garages.length; i++) {
            if (garages[i] != null && garages[i].getId().equals(garage.getId())) {
                garages[i] = garage;
                break;
            }
        }
    }

    public void attachCarToGarage(Integer carId, Integer garageId) {
        boolean hasSuchElement = false;
        for (CarGarage carGarage : carGarages) {
            if (carGarage != null) {
                if (carGarage.getCarId().equals(carId) && carGarage.getGarageId().equals(garageId)) {
                    hasSuchElement = true;
                }
            }
        }
        if (!hasSuchElement) {
            countOfCarGarages += 1;
            if (countOfCarGarages == carGarages.length) {
                updateSizeOfCarGarageArray();
            }
            CarGarage carGarage = new CarGarage();
            carGarage.setCarId(carId);
            carGarage.setGarageId(garageId);
            for (int i = 0; i < carGarages.length; i++) {
                if (carGarages[i] == null) {
                    carGarages[i] = carGarage;
                    break;
                }
            }
        }
    }

    public Car[] findAllCarsByGarageId(Integer garageId) {
        Integer countOfCar = 0;
        Integer[] carIdArray = new Integer[carGarages.length];
        for (int i = 0; i < carGarages.length; i++) {
            if (carGarages[i] != null) {
                if (carGarages[i].getGarageId().equals(garageId)) {
                    carIdArray[countOfCar] = carGarages[i].getCarId();
                    countOfCar++;
                }
            }
        }

        Car[] carsByGarageId = new Car[countOfCar];

        for (int i = 0; i < carIdArray.length; i++) {
            for (int j = 0; j < cars.length; j++) {
                if (cars[j] != null) {
                    if (cars[j].getId().equals(carIdArray[i])) {
                        carsByGarageId[i] = cars[j];
                    }
                }
            }
        }

        return carsByGarageId;
    }

    public void deleteCar(Integer carId) {
        Car[] newCars = new Car[cars.length];
        int j = 0;
        for (int i = 0; i < cars.length; i++, j++) {
            if (cars[i] != null) {
                if (cars[i].getId().equals(carId)) {
                    if (i != cars.length - 1) {
                        i++;
                    } else {
                        newCars[j] = null;
                        break;
                    }
                }
                newCars[j] = cars[i];
            } else {
                break;
            }

        }
        cars = newCars;

        CarGarage[] newCarGarages = new CarGarage[carGarages.length];
        int k = 0;
        for (int i = 0; i < carGarages.length; i++, k++) {
            if (carGarages[i] != null) {
                if (carGarages[i].getCarId().equals(carId)) {
                    if (i != carGarages.length - 1) {
                        i++;
                    } else {
                        newCarGarages[j] = null;
                        break;
                    }
                }
                newCarGarages[k] = carGarages[i];
            } else {
                break;
            }

        }
        carGarages = newCarGarages;
    }

    public void deleteGarage(Integer garageId) {
        Garage[] newGarages = new Garage[garages.length];
        int j = 0;
        for (int i = 0; i < garages.length; i++, j++) {
            if (garages[i] != null) {
                if (garages[i].getId().equals(garageId)) {
                    if (i != garages.length - 1) {
                        i++;
                    } else {
                        newGarages[j] = null;
                        break;
                    }
                }
                newGarages[j] = garages[i];
            } else {
                break;
            }

        }
        garages = newGarages;

        CarGarage[] newCarGarages = new CarGarage[carGarages.length];
        int k = 0;
        for (int i = 0; i < carGarages.length; i++, k++) {
            if (carGarages[i] != null) {
                if (carGarages[i].getGarageId().equals(garageId)) {
                    if (i != carGarages.length - 1) {
                        i++;
                    } else {
                        newCarGarages[j] = null;
                        break;
                    }
                }
                newCarGarages[k] = carGarages[i];
            } else {
                break;
            }

        }
        carGarages = newCarGarages;
    }

    public void deleteCarGarageByTwoId(Integer carId, Integer garageId) {
        CarGarage[] newCarGarages = new CarGarage[carGarages.length];
        int j = 0;
        for (int i = 0; i < carGarages.length; i++, j++) {
            if (carGarages[i] != null) {
                if (carGarages[i].getCarId().equals(carId) && carGarages[i].getGarageId().equals(garageId)) {
                    if (i != carGarages.length - 1) {
                        i++;
                    } else {
                        newCarGarages[j] = null;
                        break;
                    }
                }
                newCarGarages[j] = carGarages[i];
            } else {
                break;
            }

        }
        carGarages = newCarGarages;
    }

    public Car findCarById(int id) {
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] != null && cars[i].getId() == id) {
                return cars[i];
            }
        }
        return null;
    }

    public Garage findGarageById(int id) {
        for (int i = 0; i < garages.length; i++) {
            if (garages[i] != null && garages[i].getId() == id) {
                return garages[i];
            }
        }
        return null;
    }

    public Car[] allCars() {
        return cars;
    }

    public Garage[] allGarage() {
        return garages;
    }

    public CarGarage[] allCarGarage() {
        return carGarages;
    }

    private Integer generateIdForCar() {
        return carId++;
    }

    private Integer generateIdForGarage() {
        return garageId++;
    }

    private void updateSizeOfCarArray() {
        Car[] biggerArray = new Car[cars.length + 10];
        for (int i = 0; i < cars.length; i++) {
            biggerArray[i] = cars[i];
        }
        cars = biggerArray;
    }

    private void updateSizeOfGarageArray() {
        Garage[] biggerArray = new Garage[garages.length + 10];
        for (int i = 0; i < garages.length; i++) {
            biggerArray[i] = garages[i];
        }
        garages = biggerArray;
    }

    private void updateSizeOfCarGarageArray() {
        CarGarage[] biggerArray = new CarGarage[carGarages.length + 10];
        for (int i = 0; i < carGarages.length; i++) {
            biggerArray[i] = carGarages[i];
        }
        carGarages = biggerArray;
    }

}
