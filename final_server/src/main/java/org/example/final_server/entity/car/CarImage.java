package org.example.final_server.entity.car;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.final_server.entity.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "car_images")
public class CarImage extends BaseEntity {

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "main_image", nullable = false)
    private Boolean mainImage;

    @ManyToMany(mappedBy = "carImages")
    private Set<Car> cars;

    public CarImage(){
        this.cars = new HashSet<>();
        this.mainImage = false;
    }

}
