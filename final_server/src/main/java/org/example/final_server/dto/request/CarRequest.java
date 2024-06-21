package org.example.final_server.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.type.BodyType;
import org.example.final_server.type.BrandType;


@Getter
@Setter
public class CarRequest extends ApiRequest {

    @NotBlank(message = "brand is not present")
    private BrandType brand;

    @NotBlank(message = "brand is not present")
    private String model;

    @NotBlank(message = "brand is not present")
    private String generation;

    @NotBlank(message = "brand is not present")
    private BodyType bodyType;

    @NotBlank(message = "brand is not present")
    private String description;
}
