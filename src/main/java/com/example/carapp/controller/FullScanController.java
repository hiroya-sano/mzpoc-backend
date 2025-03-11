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
import java.util.Arrays;

@RestController
@RequestMapping("/fullscan")
@CrossOrigin
public class FullScanController {

    private static final Logger logger = LoggerFactory.getLogger(SleepController.class);

    @Autowired
    private HeavyDataRepository heavyDataRepository;

    @GetMapping
    public ResponseEntity<?> fullScan(@RequestParam(value = "description", required = true) String description) {
        try {
            List<HeavyData> hd;
            hd = heavyDataRepository.findByDescriptionContainingIgnoreCase(description);
            if (hd.isEmpty()) {
                logger.info("No heavy data records found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No heavy data records found.");
            }
            logger.info("Heavy data records retrieved successfully {}.", hd);
            return ResponseEntity.ok(hd);
        } catch (Exception e) {
            logger.error("Error while retrieving heavy data records.", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving heavy data records: " + e.getMessage());
        }
    }
}
