package org.example.service;

import org.example.db.CarGarageRelation;
import org.example.entity.Car;

public class CarService {
    private final CarGarageRelation relation = CarGarageRelation.getInstance();

    public void create(Car car){
        if(car.getBrand() != null && car.getYearOfCreating() >0){
            relation.createCar(car);
        }
    }
    public void update(Car car){
        Car current = relation.findCarById(car.getId());
        if(current != null){
            relation.updateCar(car);
        }
    }
    public void delete(int id){
        Car current = relation.findCarById(id);
        if(current != null){
            relation.deleteCar(id);
        }
    }
    public Car[] allCarsByGarage(int id){
        return relation.findAllCarsByGarageId(id);
    }
    public Car[] findAll(){
        return relation.allCars();
    }
    public Car findById(int id){
        return relation.findCarById(id);
    }
}
