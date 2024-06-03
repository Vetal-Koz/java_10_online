package org.example.final_server.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.car.Car;
import org.example.final_server.entity.car.CarImage;
import org.example.final_server.entity.car.CarVariant;
import org.example.final_server.type.BodyType;
import org.example.final_server.type.BrandType;
import org.springframework.util.CollectionUtils;

import java.util.Set;


@Getter
@Setter
public class CarResponse extends ApiResponse{
    private String brand;

    private String model;

    private String generation;

    private String bodyType;

    private String carImage;

    private String maxPrice;

    private String minPrice;

    public CarResponse(Car car){
        setId(car.getId());
        this.brand = car.getBrand().getBrand();
        this.model = car.getModel();
        this.generation = car.getGeneration();
        this.bodyType = car.getBodyType().getName();
        Set<CarImage> carImages = car.getCarImages();
        if (!CollectionUtils.isEmpty(carImages)) {
            this.carImage = carImages
                    .stream()
                    .filter(image -> image.getMainImage().equals(true))
                    .toList()
                    .getFirst()
                    .getImageUrl();
        }
    }
}
