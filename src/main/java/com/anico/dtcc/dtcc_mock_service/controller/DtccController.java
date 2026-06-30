package com.anico.dtcc.dtcc_mock_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.anico.dtcc.dtcc_mock_service.dto.UserRequest;
import com.anico.dtcc.dtcc_mock_service.dto.UserResponse;
import com.anico.dtcc.dtcc_mock_service.service.PasswordService;

import com.anico.dtcc.dtcc_mock_service.dto.NewPassRequest;
import com.anico.dtcc.dtcc_mock_service.dto.UpdatePassRequest;


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
    public String resetPass(@RequestBody NewPassRequest newPassword){
        
        return passwordService.updatePassword(newPassword);

        // //will update this later with logic to check for password conditions
        // if (newPassword.length() < 8) {
        //     return "password must be greater then 8 characters";
        // } else {
        //     return "password successfully reset!";
        // }
    }

    
    // use this method to manually change password
    @PostMapping("/change-password")
    public String resetPass(@RequestBody UpdatePassRequest request){
        
        return passwordService.changePassword(request);
    }

    //get method for testing purpose - get current password - using hashmap, not db
    @GetMapping("/get-password")
    public String getPassword(@RequestParam("userName") String userName) {
        return passwordService.getPassword(userName);
    }

  
}

//Future implementations:
// Flow:

// App sends username/password
// Gets OAuth token
// Calls API using token
// DTCC knows which user from token