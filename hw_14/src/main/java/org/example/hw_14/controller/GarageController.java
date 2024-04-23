package org.example.hw_14.controller;

import lombok.RequiredArgsConstructor;
import org.example.hw_14.dto.DataTableRequest;
import org.example.hw_14.dto.request.GarageRequest;
import org.example.hw_14.dto.response.CarResponse;
import org.example.hw_14.dto.response.GarageResponse;
import org.example.hw_14.facade.GarageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/garages")
public class GarageController {

    @Autowired
    private final GarageFacade garageFacade;

    @GetMapping
    public ResponseEntity<Collection<GarageResponse>> getAllGarages(@RequestParam Optional<Integer> page, @RequestParam(name = "limit") Optional<Integer> size) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        if (page.isPresent() && size.isPresent()) {
            dataTableRequest.setPage(page.get());
            dataTableRequest.setSize(size.get());
        }
        return ResponseEntity.ok(garageFacade.findAll(dataTableRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageResponse> getGarageById(@PathVariable Long id) {
        return ResponseEntity.ok(garageFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createGarage(@RequestBody GarageRequest garageRequest) {
        garageFacade.create(garageRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGarage(@RequestBody GarageRequest garageRequest, @PathVariable Long id) {
        garageFacade.update(garageRequest, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarage(@PathVariable Long id) {
        garageFacade.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/attached")
    public ResponseEntity<Collection<CarResponse>> getCarsByGarageId(@PathVariable Long id) {
        return ResponseEntity.ok(garageFacade.findCarsByGarageId(id));
    }

    @PostMapping("{id}/attached/{carId}")
    public ResponseEntity<Void> attachCarToGarage(@PathVariable(name = "id") Long garageId, @PathVariable Long carId) {
        garageFacade.attachCarToDepartment(garageId, carId);
        return ResponseEntity.ok().build();
    }
}
