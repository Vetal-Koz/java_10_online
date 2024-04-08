package org.example.repository;

import org.example.entity.Garage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GarageRepository extends JpaRepository<Garage, Long> {
}
