package com.sample.bidsystem.controller;

import javax.validation.Valid;

import com.sample.bidsystem.repository.UserRepository;
import com.sample.bidsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.bidsystem.model.request.LoginForm;
import com.sample.bidsystem.model.request.RegisterForm;
import com.sample.bidsystem.model.response.TokenResponse;

@CrossOrigin(origins = "*", maxAge = 7200)
@RestController
@RequestMapping("/api/v1/auth")
public class UserLoginController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        String jwt = userService.generateToken(loginRequest);

        return ResponseEntity.ok(new TokenResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterForm registerFormRequest) {

        if(userRepository.existsByUsername(registerFormRequest.getUsername())) {
            return new ResponseEntity<String>("Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(registerFormRequest.getEmail())) {
            return new ResponseEntity<String>("Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        userService.addUser(registerFormRequest);

        return ResponseEntity.ok().body("User registered!");
    }
}