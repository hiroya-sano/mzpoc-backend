package com.example.carapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.carapp.model.Car;
import com.example.carapp.repository.CarRepository;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin // 個別のエンドポイントでCORS対応（もしくはグローバル設定でも可）
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
