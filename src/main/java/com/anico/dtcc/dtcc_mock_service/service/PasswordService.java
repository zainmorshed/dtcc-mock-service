package com.anico.dtcc.dtcc_mock_service.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.anico.dtcc.dtcc_mock_service.dto.UserRequest;

@Service
public class PasswordService {

    private final LocalDate currentDate = LocalDate.now();

    private final Map<String, LocalDate> validUsers = Map.of(
        "ANICO", LocalDate.of(2026, 6, 28),
        "MS", LocalDate.of(2026, 7, 14),
        "DTCC", LocalDate.of(2026, 7, 28)
    );
    //^ factory methods allow us to intialize the objects we create - that is their main job - they take your inputs and set up the objects initial state

    public PasswordService() {
    }
    
    public boolean checkValidUser(UserRequest userRequest) {

        for(String i : validUsers.keySet()) {
            //check valid user
            if(i.equals(userRequest.getUser())) {
                return true;
            } 
            //can also use return validUsers.containsKey(user); -> instantly returns true or false
        }
        return false;
        
    }

    public String checkValidPassword(UserRequest userRequest) {

        boolean valUser = checkValidUser(userRequest);

        if (valUser) {
            LocalDate expirationDate = validUsers.get(userRequest.getUser());

            if(expirationDate.isBefore(currentDate)) {
                return "Password Expired!";
            } else if (expirationDate.isEqual(currentDate) || expirationDate.isAfter(currentDate.minusDays(5))) {
                return "Password will soon expire";
            }
        }
        return "Password is still valid!";

    }
}
