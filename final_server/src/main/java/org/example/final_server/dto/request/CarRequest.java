package org.example.final_server.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.type.BodyType;
import org.example.final_server.type.BrandType;


@Getter
@Setter
public class CarRequest extends ApiRequest{
    private BrandType brand;

    private String model;

    private String generation;

    private BodyType bodyType;

    private String description;
}
