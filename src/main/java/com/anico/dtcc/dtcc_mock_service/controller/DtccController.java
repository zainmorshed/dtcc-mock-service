package com.anico.dtcc.dtcc_mock_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.anico.dtcc.dtcc_mock_service.dto.UserRequest;
import com.anico.dtcc.dtcc_mock_service.dto.UserResponse;
import com.anico.dtcc.dtcc_mock_service.service.PasswordService;

@RestController
public class DtccController {

    private final PasswordService passwordService;

    public DtccController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping("/password-expiration")    
    public UserResponse checkPassExp(@RequestBody UserRequest userRequest) {
        return passwordService.checkValidPassword(userRequest);
    }

    @PostMapping("/reset-password")
    public String resetPass(@RequestBody String newPassword){


        //will update this later with logic to check for password conditions
        if (newPassword.length() < 8) {
            return "password must be greater then 8 characters";
        } else {
            return "password successfully reset!";
        }
    }
  
}

//Future implementations:
// Flow:

// App sends username/password
// Gets OAuth token
// Calls API using token
// DTCC knows which user from token