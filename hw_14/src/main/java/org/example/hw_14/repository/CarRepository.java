package org.example.hw_14.repository;

import org.example.hw_14.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Car, Long> {

}
