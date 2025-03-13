package com.example.carapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.carapp.model.HeavyData;
import com.example.carapp.repository.HeavyDataRepository;

import java.util.List;


@RestController
@RequestMapping("/heavyData")
@CrossOrigin
public class HeavyDataController {

    private static final Logger logger = LoggerFactory.getLogger(HeavyDataController.class);
    private static final int DEPTH = 9;

    @Autowired
    private HeavyDataRepository heavyDataRepository;

    @GetMapping
    public ResponseEntity<?> getHeavyData() {
        try {
            List<HeavyData> hds;
            logger.info("Get heavy data Called.");
            hds = heavyDataRepository.findAll(DEPTH);
            logger.info("Getted heavy data. {}", hds);
            return ResponseEntity.ok(hds);
        } catch (Exception e) {
            logger.error("Error while cte: {}", e.getMessage(), e);
            throw new RuntimeException("Intentional heavy data query error.");
        }
    }
}
