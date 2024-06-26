package org.example.final_server.dto.response;


import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.car.CarVariant;


@Getter
@Setter
public class CarVariantResponse extends ApiResponse {

    private String color;

    private Integer year;

    private String kilometrage;

    private String transmission;

    private String drivetrain;

    private String price;

    private Integer numberOfDoors;

    private Integer numberOfSeats;

    private String safetyRating;

    private CarEngineResponse carEngineResponse;

    private CarResponse car;

    public CarVariantResponse(CarVariant carVariant) {
        setId(carVariant.getId());
        this.color = carVariant.getColor();
        this.year = carVariant.getYear();
        this.kilometrage = String.valueOf(carVariant.getKilometrage());
        this.transmission = carVariant.getTransmission().getType();
        this.drivetrain = carVariant.getDrivetrain().getType();
        this.price = String.valueOf(carVariant.getPrice());
        this.numberOfDoors = carVariant.getNumberOfDoors();
        this.numberOfSeats = carVariant.getNumberOfSeats();
        this.safetyRating = String.valueOf(carVariant.getSafetyRating());
        if (carVariant.getCarEngine() != null) {
            this.carEngineResponse = new CarEngineResponse(carVariant.getCarEngine());
        }
        this.car = new CarResponse(carVariant.getCar());

    }
}
