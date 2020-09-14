package com.sample.bidsystem.controller;

import com.sample.bidsystem.security.jwt.JwtTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class ApplicationController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @GetMapping("/check")
    public ResponseEntity<String> check(){
        logger.info("Health check called");
       return ResponseEntity.ok().body("Success");
    }
}
