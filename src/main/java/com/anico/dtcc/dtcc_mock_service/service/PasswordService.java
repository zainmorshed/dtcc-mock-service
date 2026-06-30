package com.anico.dtcc.dtcc_mock_service.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.anico.dtcc.dtcc_mock_service.dto.NewPassRequest;
import com.anico.dtcc.dtcc_mock_service.dto.UpdatePassRequest;
import com.anico.dtcc.dtcc_mock_service.dto.UserRequest;
import com.anico.dtcc.dtcc_mock_service.dto.UserResponse;
import com.anico.dtcc.dtcc_mock_service.model.DtccUser;

@Service
public class PasswordService {

    private final LocalDate currentDate = LocalDate.now();

    // private final Map<String, LocalDate> validUsers = Map.of(
    //     "ANICO", LocalDate.of(2026, 6, 30),
    //     "MS", LocalDate.of(2026, 7, 14),
    //     "DTCC", LocalDate.of(2026, 7, 28)
    // );
    //^ factory methods allow us to intialize the objects we create - that is their main job - they take your inputs and set up the objects initial state

    private final Map<String, DtccUser> users = new HashMap<>();
    //initial map state
    public PasswordService() {
        users.put("anico-service-account", new DtccUser(
                                            "ANICO",
                                            "password123",
                                            LocalDate.now().plusDays(1)));
        users.put("ms-service-account", new DtccUser(
                                    "MS",
                                    "password789",
                                    LocalDate.now().plusDays(10)));
        users.put("dtcc-service-account", new DtccUser(
                                    "DTCC",
                                    "password456",
                                    LocalDate.now().plusDays(20)));
                                                                        
    }

    public String getPassword(String userName){

        String accountKey = userName.toLowerCase() + "-service-account";
        DtccUser account = users.get(accountKey);
        String password = account.getPassword();

        return password;

    }

    public String updatePassword(NewPassRequest newPassword) {
        String userName = newPassword.getUserName();
        String updatedPassword = newPassword.getNewPassword();

        // DtccUser dttcUser = new DtccUser();
        // dtccUser.setPassword(updatedPassword);
        // users.put
        String accountKey = userName.toLowerCase() + "-service-account";
        DtccUser userAccount = users.get(accountKey);

        if (userAccount != null) {
            userAccount.setPassword(updatedPassword);
            userAccount.setExpirationDate(LocalDate.now().plusDays(30));

            return "Password successfully reset for " + userName + "!";
        }
        return "Error: Account not found!";
            
    }

    //this method is used to support manual password reset
    
    public String changePassword(UpdatePassRequest request) {
        String userName = request.getUserName();
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();

        String accountKey = userName.toLowerCase() + "-service-account";
        DtccUser userAccount = users.get(accountKey);

        if(userAccount != null) {
            userAccount.setPassword(newPassword);
            userAccount.setExpirationDate(LocalDate.now().plusDays(30));

            return "Password successfully reset for " + userName + "!";
        }
        return "Error: Account not found!";
    }
    
    // public boolean checkValidUser(UserRequest userRequest) {

    //     for(String i : users.keySet()) {
    //         //check valid user
    //         if(i.equals(userRequest.getUserName())) {
    //             return true;
    //         } 
    //         //can also use return validUsers.containsKey(user); -> instantly returns true or false
    //     }
    //     return false;
        
    // }


    public UserResponse checkValidPassword(UserRequest userRequest) {
        //object instationation inside method to ensure every API call gets its own clean, isolated response container - sol multiple users don't overwrite each other data
        UserResponse userResponse = new UserResponse();

        // boolean valUser = checkValidUser(userRequest);
        // private final DtccUser dtccUser = new DtccUser(userRequest.getUserName(), userRequest.getPassword(), currentDate);

        DtccUser user = users.get(userRequest.getUserName());
        if (user == null) {
            userResponse.setPasswordStatus("Invalid User!");
            return userResponse;
        }

        LocalDate expirationDate = user.getExpirationDate(); //check expiration date after we've confirmed the user exists

        userResponse.setUserName(userRequest.getUserName());
        userResponse.setExpirationDate(expirationDate);

        
        if(expirationDate.isBefore(currentDate)) {
            userResponse.setPasswordStatus("Password Expired!");
            return userResponse;

        } else if (expirationDate.isEqual(currentDate)) {
            userResponse.setPasswordStatus("Password will expire today! Please reset");
            return userResponse;
        } else if (expirationDate.isBefore(currentDate.plusDays(5))){ //rotate the password within 5 days of the expiration date

            long daysRemaining = ChronoUnit.DAYS.between(currentDate, expirationDate);
            userResponse.setPasswordStatus("Password will expire in " + daysRemaining + " days! ");
            return userResponse;
        } 
    
    userResponse.setPasswordStatus("Password is still valid until "+ expirationDate);
    return userResponse;

    }
}
