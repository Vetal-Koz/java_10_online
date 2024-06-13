package org.example.final_server.repository.car.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.car.Car;
import org.example.final_server.type.FuelType;
import org.example.final_server.type.TransmissionType;


@Getter
@Setter
@AllArgsConstructor
@Builder()
public class CarSearchDto {
    private Car car;
    private TransmissionType transmissionType;
    private FuelType fuelType;
}
