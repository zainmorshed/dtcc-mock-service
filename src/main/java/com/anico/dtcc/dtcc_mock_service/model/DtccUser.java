package com.anico.dtcc.dtcc_mock_service.model;

import java.time.LocalDate;

public class DtccUser {
    

    private String userName;
    private String password;
    private LocalDate expirationDate;


    public DtccUser(String userName, String password, LocalDate expirationDate) {
        this.userName = userName;
        this.password = password;
        this.expirationDate = expirationDate;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public LocalDate getExpirationDate() {
        return expirationDate;
    }


    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }


    
}
