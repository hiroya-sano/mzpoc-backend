package com.example.carapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.carapp.model.Car;
import com.example.carapp.repository.CarRepository;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin // CORS設定
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarRepository carRepository;

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car car) {
        try {
            Car savedCar = carRepository.save(car);
            // 成功時のログ出力
            logger.info("Car registered successfully: {}", savedCar);
            return ResponseEntity.ok(savedCar);
        } catch (Exception e) {
            // 例外発生時のエラーログ出力
            logger.error("Error during car registration: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Car registration failed: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCars() {
        try {
            List<Car> cars = carRepository.findAll();
            if (cars.isEmpty()) {
                // 業務エラー：データが存在しない場合のログ出力
                logger.info("No car records found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No car records found.");
            }
            // 成功時のログ出力
            logger.info("Car records retrieved successfully: {}", cars);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            // 例外発生時のエラーログ出力
            logger.error("Error while retrieving car records: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving car records: " + e.getMessage());
        }
    }
}
