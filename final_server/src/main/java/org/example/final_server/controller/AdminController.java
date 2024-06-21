package org.example.final_server.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.CarRequest;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.dto.response.DataTableResponse;
import org.example.final_server.dto.response.ResponseContainer;
import org.example.final_server.dto.response.UserResponse;
import org.example.final_server.facade.CarFacade;
import org.example.final_server.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
@Validated
public class AdminController {

    private final UserFacade userFacade;
    private final CarFacade carFacade;

    @GetMapping("/users")
    public ResponseEntity<ResponseContainer<DataTableResponse<UserResponse>>> findAllUsers(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "desc") String order,
            @RequestParam(defaultValue = "id") String sort) {
        DataTableRequest request = new DataTableRequest(size, page, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(userFacade.findAll(request)));
    }

    @DeleteMapping("/users/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId) {
        userFacade.delete(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/{id}")
    private ResponseEntity<ResponseContainer<UserResponse>> findUserById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(new ResponseContainer<>(userFacade.findById(id)));
    }

    @GetMapping("/users/{email}")
    private ResponseEntity<ResponseContainer<UserResponse>> findUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(new ResponseContainer<>(userFacade.getUserByEmail(email)));
    }

    @PostMapping("/cars")
    private ResponseEntity<?> createCar(@Valid @RequestBody CarRequest carRequest) {
        carFacade.create(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/cars/{id}")
    private ResponseEntity<?> updateCar(@Valid @RequestBody CarRequest carRequest,
                                        @PathVariable(name = "id") Long carId) {
        carFacade.update(carRequest, carId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/cars/{id}")
    private ResponseEntity<?> deleteCar(@PathVariable(name = "id") Long carId) {
        carFacade.delete(carId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
