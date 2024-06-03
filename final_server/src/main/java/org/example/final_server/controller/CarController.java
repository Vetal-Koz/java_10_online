package org.example.final_server.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.dto.response.CarResponse;
import org.example.final_server.dto.response.DataTableResponse;
import org.example.final_server.dto.response.ResponseContainer;
import org.example.final_server.facade.CarFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Car Page", description = "the plp API with crud operations")
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarFacade carFacade;

    @GetMapping
    public ResponseEntity<ResponseContainer<DataTableResponse<CarResponse>>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String order){
        DataTableRequest dataTableRequest = new DataTableRequest(size, page, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(carFacade.findAll(dataTableRequest)));
    }
}
