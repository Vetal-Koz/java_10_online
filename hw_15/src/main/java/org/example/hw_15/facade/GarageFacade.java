package org.example.hw_15.facade;


import org.example.hw_15.dto.request.GarageRequest;
import org.example.hw_15.dto.response.CarResponse;
import org.example.hw_15.dto.response.GarageResponse;

import java.util.Collection;
import java.util.List;

public interface GarageFacade extends CrudFacade<GarageRequest, GarageResponse> {
    void attachCarToDepartment(Long garageId, Long carId);

    void attachCarsToGarage(Long garageId, List<Long> carIds);

    void detachCarsFromGarage(Long garageId, List<Long> carIds);

    List<CarResponse> findByNotAttachToGarage(Long garageId);

    Collection<CarResponse> findByAttachToGarage(Long id);
}
