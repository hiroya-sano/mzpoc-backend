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
@RequestMapping("/memoryLeak")
@CrossOrigin
public class MemoryLeakController {

    private static final Logger logger = LoggerFactory.getLogger(SleepController.class);
    private static final List<byte[]> leakList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<?> leakMemory() {
        int counter = 0;
        try {
            while (true) {
                byte[] data = new byte[50 * 1024 * 1024];
                leakList.add(data);
                logger.info("Allocated " + (50 * (++counter)) + " MB");

                if (counter % 10 == 0) {
                    leakList.clear();
                    logger.info("Cleared leakList after " + counter + " allocations");
                }
                
                System.gc();
                Thread.sleep(50);
            }
        } catch (OutOfMemoryError e) {
            logger.error("OutOfMemoryError: {}", e.getMessage(), e);
            throw new RuntimeException("Intentional memory leak error(memory leak).");
        } catch (InterruptedException e) {
            logger.error("Error: {}", e.getMessage(), e);
            throw new RuntimeException("Intentional memory leak error(memory leak).");
        }
    }

    @GetMapping("/more")
    public ResponseEntity<?> leakMemoryMore() {
        int counter = 0;
        try {
            byte[] data = new byte[200 * 1024 * 1024];
            leakList.add(data);
            logger.info("Allocated " + (100 * (++counter)) + " MB");
            return ResponseEntity.ok("memory leak more finish.");
        } catch (OutOfMemoryError e) {
            logger.error("OutOfMemoryError: {}", e.getMessage(), e);
            throw new RuntimeException("Intentional memory leak error(memory leak).");
        }
    }
    
    @GetMapping("/release")
    public ResponseEntity<?> release() {
        List<Car> ml = new ArrayList<>();
        leakList.clear();
        System.gc();
        logger.info("Memory released.");
        return ResponseEntity.ok(ml);
    }
}
