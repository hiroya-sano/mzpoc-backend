package com.example.carapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carapp.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
