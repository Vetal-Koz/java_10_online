package org.example.hw_15.repository;


import org.example.hw_15.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "select * from cars where id not in (select gc.car_id from cars inner join public.gar_car gc on cars.id = gc.car_id where gc.gar_id = ?1)", nativeQuery = true)
    List<Car> findByNonAttachToGarage(Long garageId);

    List<Car> findAllByIdIn(List<Long> carIds);

    @Query(value = "select cars.id, brand, year_of_creating from cars inner join public.gar_car gc on cars.id = gc.car_id where gc.gar_id = ?1", nativeQuery = true)
    List<Car> findByAttachToGarage(Long garageId);
}
