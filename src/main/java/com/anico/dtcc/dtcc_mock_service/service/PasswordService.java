package com.anico.dtcc.dtcc_mock_service.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Service;

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
    
    public boolean checkValidUser(String user) {

        for(String i : validUsers.keySet()) {
            //check valid user
            if(i.equals(user)) {
                return true;
            } 
            //can also use return validUsers.containsKey(user); -> instantly returns true or false
        }
        return false;
        
    }

    public String checkValidPassword(String user) {

        boolean valUser = checkValidUser(user);

        if (valUser) {
            LocalDate expirationDate = validUsers.get(user);

            if(expirationDate.isBefore(currentDate)) {
                return "Password Expired!";
            } else if (expirationDate.isEqual(currentDate) || expirationDate.isAfter(currentDate.minusDays(5))) {
                return "Password will soon expire";
            }
        }
        return "Password is still valid!";

    }
}
