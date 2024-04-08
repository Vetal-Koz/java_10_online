package org.example.repository;

import org.example.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Collection;

public interface CarRepository extends JpaRepository<Car, Long> {
    Collection<Car> findByGaragesIdIn(Collection<Long> longs);

}
