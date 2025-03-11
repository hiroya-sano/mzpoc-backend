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
import java.util.ArrayList;
import java.lang.Thread;


@RestController
@RequestMapping("/sleep")
@CrossOrigin
public class SleepController {

    private static final Logger logger = LoggerFactory.getLogger(SleepController.class);
    private static final int SLEEP_MS = 1000;

    @GetMapping
    public ResponseEntity<?> sleep() {
        try {
            List<Car> sleep = new ArrayList<>();
            Thread.sleep(SLEEP_MS);
            logger.info("Speel Called.");
            return ResponseEntity.ok(sleep);
        } catch (Exception e) {
            logger.error("Error during Sleep Called.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Car registration failed: " + e.getMessage());
        }
    }

    @GetMapping("/multi")
    public ResponseEntity<?> sleepMulti() {
        try {
            List<Car> sleep = new ArrayList<>();
            Thread.sleep(SLEEP_MS);
            logger.info("Speel Called.");
            return ResponseEntity.ok(sleep);
        } catch (Exception e) {
            logger.error("Error during Sleep Called.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Car registration failed: " + e.getMessage());
        }
    }
}
