package org.example.db.impl;

import com.google.gson.Gson;
import org.example.db.FileDb;
import org.example.entity.Car;
import org.example.entity.Garage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class GarageJsonFileDb implements FileDb<Garage> {

    private final List<Garage> garages = new ArrayList<>();
    private Long garageId = 0L;

    @Override
    public void create(Garage entity) {
        readJson();
        entity.setId(generateId());
        garages.add(entity);
        writeJson();
    }

    @Override
    public void update(Garage entity) {
        readJson();
        Optional<Garage> oldGarageOptional = findById(entity.getId());
        if (oldGarageOptional.isPresent()) {
            Garage oldGarage = oldGarageOptional.get();
            oldGarage.setName(entity.getName());
        }
        writeJson();
    }

    @Override
    public void delete(Long id) {
        readJson();
        Optional<Garage> deleteGarageOptional = findById(id);
        if (deleteGarageOptional.isPresent()) {
            Garage deleteGarage = deleteGarageOptional.get();
            garages.remove(deleteGarage);
        }
        CarGarageJsonFileDb carGarageJsonFileDb = new CarGarageJsonFileDb();
        carGarageJsonFileDb.deleteAllByGarageId(id);
        writeJson();
    }

    @Override
    public Optional<Garage> findById(Long id) {
        return garages.stream()
                .filter(garage -> garage.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Garage> findAll() {
        readJson();
        return garages;
    }

    private void readJson() {
        Gson gson = new Gson();
        try {
            Garage[] from = gson.fromJson(new FileReader("garages.json"), Garage[].class);
            if (from != null) {
                this.garages.clear();
                this.garages.addAll(List.of(from));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("garages.json")) {
            String json = gson.toJson(this.garages);
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long generateId() {
        readJson();
        if (!garages.isEmpty()) {
            garageId = garages.getLast().getId();
        }
        return garageId = garageId + 1;
    }
}
