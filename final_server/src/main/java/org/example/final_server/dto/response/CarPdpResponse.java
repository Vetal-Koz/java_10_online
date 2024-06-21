package org.example.final_server.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.car.Car;
import org.example.final_server.entity.car.CarImage;
import org.example.final_server.entity.car.CarVariant;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Getter
@Setter
public class CarPdpResponse extends ApiResponse {
    private String brand;

    private String model;

    private String description;

    private String generation;

    private String bodyType;

    private List<String> carImages;

    private List<CarVariantResponse> carVariantResponses;

    public CarPdpResponse(Car car, List<CarVariant> carVariants) {
        setId(car.getId());
        this.brand = car.getBrand().getBrand();
        this.description = car.getDescription();
        this.model = car.getModel();
        this.generation = car.getGeneration();
        this.bodyType = car.getBodyType().getName();
        if (!CollectionUtils.isEmpty(car.getCarImages())) {
            this.carImages = car.getCarImages().stream().map(CarImage::getImageUrl).toList();
        }
        if (!CollectionUtils.isEmpty(carVariants)) {
            this.carVariantResponses = carVariants.stream().map(CarVariantResponse::new).toList();
        }
    }


}
