package com.example.carapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carapp.model.Car;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByModelContainingIgnoreCase(String model);
}