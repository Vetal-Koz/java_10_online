package org.example.final_server.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.final_server.dto.request.DataTableRequest;
import org.example.final_server.dto.response.CarPdpResponse;
import org.example.final_server.dto.response.CarResponse;
import org.example.final_server.dto.response.DataTableResponse;
import org.example.final_server.dto.response.ResponseContainer;
import org.example.final_server.elastic.document.CarIndex;
import org.example.final_server.facade.CarFacade;
import org.example.final_server.facade.CarPdpFacade;
import org.example.final_server.service.CarSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Car Page", description = "the plp API with crud operations")
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@Validated
public class CarController {

    private final CarFacade carFacade;
    private final CarPdpFacade carPdpFacade;
    private final CarSearchService carSearchService;

    @GetMapping
    public ResponseEntity<ResponseContainer<DataTableResponse<CarResponse>>> findAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "desc") String order){
        DataTableRequest dataTableRequest = new DataTableRequest(size, page, sort, order);
        return ResponseEntity.ok(new ResponseContainer<>(carFacade.findAll(dataTableRequest)));
    }

    @GetMapping("{carId}")
    public ResponseEntity<ResponseContainer<CarPdpResponse>> findById(@Min(1) @PathVariable("carId") Long carId){
        return ResponseEntity.ok(new ResponseContainer<>(carPdpFacade.findByCar(carId)));
    }

    @GetMapping("search")
    public ResponseEntity<ResponseContainer<List<CarIndex>>> search(@RequestParam String search){
        List<CarIndex> cars = carSearchService.search(search);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseContainer<List<CarIndex>>(cars));
    }
}
