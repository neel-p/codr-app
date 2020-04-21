package com.codr.aws.entity;

public class UserInformation {

    private final String userName;
    private final String emailAddr;
    private final String location;

    public UserInformation(final String userName, final String emailAddr, final String location) {
        this.userName = userName;
        this.emailAddr = emailAddr;
        this.location = location;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public String getLocation() {
        return location;
    }

}
