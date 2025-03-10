package com.example.carapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyErrorController {

    private static final Logger logger = LoggerFactory.getLogger(DummyErrorController.class);

    @GetMapping("/dummyerrors")
    public String triggerDummyError() {
        logger.error("Dummy error endpoint accessed. Triggering intentional error for observability testing.");
        throw new RuntimeException("Intentional dummy error for observability testing.");
    }
}
