package org.example.hw_14.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequest extends ApiRequest {
    private String brand;
    private int yearOfCreating;
}
