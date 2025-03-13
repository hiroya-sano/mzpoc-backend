package com.example.carapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.carapp.model.Car;
import com.example.carapp.repository.CarRepository;

@RestController
@RequestMapping("/cpuload")
@CrossOrigin
public class CpuLoadController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @GetMapping
    public ResponseEntity<String> fibonacci(@RequestParam(name = "n", defaultValue = "40", required = false) int n) {
        logger.info("Called CPU load.");

        try {
            long start = System.currentTimeMillis();
            long result = fib(n);
            long duration = System.currentTimeMillis() - start;
            String response = String.format("fib(%d) = %d, 計算時間: %d ms", n, result, duration);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error while Fibonacci sequence: {}", e.getMessage(), e);
            throw new RuntimeException("Intentional Fibonacci sequence error.");
        }
    }

    private long fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
