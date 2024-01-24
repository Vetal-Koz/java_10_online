package org.example.service;

import org.example.db.CarGarageRelation;
import org.example.entity.CarGarage;

public class CarGarageService {
    private final CarGarageRelation relation = CarGarageRelation.getInstance();

    public CarGarage[] findAll() {
        return relation.allCarGarage();
    }

    public void deleteCarGarage(int carId, int garageId) {
        relation.deleteCarGarageByTwoId(carId, garageId);
    }
}
