package org.example.db.impl;

import com.google.gson.Gson;
import org.example.entity.CarGarage;
import org.example.factory.FileType;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CarGarageJsonFileDb {

    private List<CarGarage> carGarages = new ArrayList<>();

    public CarGarageJsonFileDb() {
        File file = new File(FileType.CAR_GARAGES_JSON.getType());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void create(CarGarage entity) {
        readJson();
        carGarages.add(entity);
        writeJson();
    }

    public void delete(Long carId, Long garageId) {
        readJson();
        CarGarage deleteCarGarage = carGarages.stream()
                .filter(carGarage -> carGarage.getGarageId().equals(garageId) && carGarage.getCarId().equals(carId))
                .findFirst()
                .get();
        carGarages.remove(deleteCarGarage);
        writeJson();
    }

    void deleteAllByCarId(Long carId) {
        readJson();
        List<CarGarage> deleteCarGarage = carGarages.stream()
                .filter(carGarage -> carGarage.getCarId().equals(carId))
                .toList();

        deleteCarGarage.stream().forEach(delete -> carGarages.remove(delete));

        writeJson();
    }

    void deleteAllByGarageId(Long garageId) {
        readJson();
        List<CarGarage> deleteCarGarage = carGarages.stream()
                .filter(carGarage -> carGarage.getGarageId().equals(garageId))
                .toList();

        deleteCarGarage.stream().forEach(delete -> carGarages.remove(delete));

        writeJson();
    }

    public Collection<CarGarage> findAll() {
        readJson();
        return carGarages;
    }

    private void readJson() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("carGarages.json")) {
            CarGarage[] array = gson.fromJson(fileReader, CarGarage[].class);
            if (array != null) {
                carGarages.clear();
                carGarages.addAll(Arrays.asList(array));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("carGarages.json")) {
            String json = gson.toJson(carGarages);
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
