package com.example.carapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.carapp.dto.HeavyDataJoinDto;
import com.example.carapp.service.HeavyDataService;

import java.util.List;


@RestController
@RequestMapping("/heavyData")
@CrossOrigin
public class HeavyDataController {

    private static final Logger logger = LoggerFactory.getLogger(HeavyDataController.class);

    @Autowired
    private HeavyDataService heavyDataService;

    @GetMapping
    public ResponseEntity<?> getHeavyData(@RequestParam(value = "description", required = true) String description) {
        List<HeavyDataJoinDto> hd; 
        hd = heavyDataService.getHeavyDataByDescription(description);
        logger.info("Get heavy data Called. {}", hd);
        return ResponseEntity.ok(hd);
    }
}
