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
    public String leakMemory() {
        leakList.add(new byte[100 * 1024 * 1024]);    // ヒープ圧迫
        logger.info("Memory leak Called.");
        return "Allocated " + (leakList.size() * 100) + " MB of memory.";
    }

    @GetMapping("/release")
    public String release() {
        leakList.clear();
        System.gc();
        return "Memory released.";
    }
}
