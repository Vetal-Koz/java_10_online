package org.example.entity;

public class CarGarage {

    private Long carId;
    private Long garageId;

    public Long getGarageId() {
        return garageId;
    }

    public void setGarageId(Long garageId) {
        this.garageId = garageId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "CarGarage{" +
                "carId=" + carId +
                ", garageId=" + garageId +
                '}';
    }
}
