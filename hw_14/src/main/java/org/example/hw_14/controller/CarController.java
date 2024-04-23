package org.example.hw_14.controller;

import lombok.RequiredArgsConstructor;
import org.example.hw_14.dto.DataTableRequest;
import org.example.hw_14.dto.request.CarRequest;
import org.example.hw_14.dto.response.CarResponse;
import org.example.hw_14.facade.CarFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private final CarFacade carFacade;

    @GetMapping
    public ResponseEntity<Collection<CarResponse>> getAllCars(@RequestParam Optional<Integer> page, @RequestParam(name = "limit") Optional<Integer> size) {
        DataTableRequest dataTableRequest = new DataTableRequest();
        if (page.isPresent() && size.isPresent()) {
            dataTableRequest.setPage(page.get());
            dataTableRequest.setSize(size.get());
        }
        return ResponseEntity.ok(carFacade.findAll(dataTableRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(carFacade.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CarRequest carRequest) {
        carFacade.create(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCar(@RequestBody CarRequest carRequest, @PathVariable Long id) {
        carFacade.update(carRequest, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carFacade.delete(id);
        return ResponseEntity.ok().build();
    }
}
