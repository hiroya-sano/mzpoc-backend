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
import java.util.Optional;

@RestController
@RequestMapping("/cars")
@CrossOrigin
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id) {
        try {
            Optional<Car> optionalCar = carRepository.findById(id);
            if (optionalCar.isPresent()) {
                logger.info("Car retrieved successfully: {}", optionalCar.get());
                return ResponseEntity.ok(optionalCar.get());
            } else {
                logger.info("Car with id {} not found.", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Car not found.");
            }
        } catch (Exception e) {
            logger.error("Error retrieving car with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving car: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        try {
            Optional<Car> optionalCar = carRepository.findById(id);
            if (optionalCar.isPresent()) {
                Car existingCar = optionalCar.get();
                existingCar.setModel(carDetails.getModel());
                existingCar.setCustom(carDetails.getCustom());
                Car updatedCar = carRepository.save(existingCar);
                logger.info("Car updated successfully: {}", updatedCar);
                return ResponseEntity.ok(updatedCar);
            } else {
                logger.info("Car with id {} not found for update.", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Car not found for update.");
            }
        } catch (Exception e) {
            logger.error("Error updating car with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating car: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id) {
        try {
            Optional<Car> optionalCar = carRepository.findById(id);
            if (optionalCar.isPresent()) {
                carRepository.deleteById(id);
                logger.info("Car deleted successfully with id: {}", id);
                return ResponseEntity.ok("Car deleted successfully.");
            } else {
                logger.info("Car with id {} not found for deletion.", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Car not found for deletion.");
            }
        } catch (Exception e) {
            logger.error("Error deleting car with id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting car: " + e.getMessage());
        }
    }
}
