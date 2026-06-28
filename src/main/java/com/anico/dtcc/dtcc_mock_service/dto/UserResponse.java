package com.anico.dtcc.dtcc_mock_service.dto;

import java.time.LocalDate;

public class UserResponse {
    private String userName;
    private LocalDate expirationDate;
    private String passwordStatus;


    public UserResponse(String userName, LocalDate expirationDate, String passwordStatus) {
        this.userName = userName;
        this.expirationDate = expirationDate;
        this.passwordStatus = passwordStatus;
    }

    public UserResponse(){}

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(String passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    
}
