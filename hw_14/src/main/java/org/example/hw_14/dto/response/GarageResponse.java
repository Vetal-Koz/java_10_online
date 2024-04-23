package org.example.hw_14.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.hw_14.entity.Garage;

@Getter
@Setter
public class GarageResponse extends ApiResponse{
    private String name;

    public GarageResponse(){};

    public GarageResponse(Garage garage){
        this.setId(garage.getId());
        this.setName(garage.getName());
    }
}
