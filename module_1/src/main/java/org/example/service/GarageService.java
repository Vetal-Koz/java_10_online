package org.example.service;

import org.example.db.CarGarageRelation;
import org.example.entity.Car;
import org.example.entity.Garage;

public class GarageService {
    private final CarGarageRelation relation = CarGarageRelation.getInstance();

    public void create(Garage garage){
        if (garage != null && garage.getName() != null){
            relation.createGarage(garage);
        }
    }
    public void update(Garage garage){
        Garage current = relation.findGarageById(garage.getId());
        if (current != null){
            relation.updateGarage(garage);
        }
    }
    public void delete(int id){
        Garage current = relation.findGarageById(id);
        if(current != null){
            relation.deleteGarage(id);
        }
    }
    public void attachCar(Integer carId, Integer garageId){
        Garage currentGarage = relation.findGarageById(garageId);
        Car currentCar = relation.findCarById(carId);
        if (currentGarage != null && currentCar != null) {
            relation.attachCarToGarage(carId, garageId);
        }
    }
    public void deleteCarGarage(int carId, int garageId){
        relation.deleteCarGarageByTwoId(carId, garageId);
    }
    public Garage[] findAll(){
        return relation.allGarage();
    }
    public Garage findById(int id){
        return relation.findGarageById(id);
    }
}
