package org.example.hw_14.facade;

import org.example.hw_14.dto.request.GarageRequest;
import org.example.hw_14.dto.response.CarResponse;
import org.example.hw_14.dto.response.GarageResponse;

import java.util.Collection;

public interface GarageFacade extends CrudFacade<GarageRequest, GarageResponse> {
    void attachCarToDepartment(Long garageId, Long carId);

    Collection<CarResponse> findCarsByGarageId(Long id);
}
