package com.anico.dtcc.dtcc_mock_service.dto;

public class NewPassRequest {
    
    private String userName;
    private String newPassword;

    public NewPassRequest(String userName, String newPassword) {
        this.userName = userName;
        this.newPassword = newPassword;
    }

    public NewPassRequest(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}


