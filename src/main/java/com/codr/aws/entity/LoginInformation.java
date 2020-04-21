package com.codr.aws.entity;


public class LoginInformation extends UserInformation {
    private Boolean newPasswordRequired = false;
    private String accessToken;

    public LoginInformation(final String userName, final String email, final String location) {
        super(userName, email, location);
    }

    public LoginInformation(final UserInformation info, final SessionInfo sessionInfo) {
        this(info.getUserName(), info.getEmailAddr(), info.getLocation());
        this.accessToken = sessionInfo.getAccessToken();
    }

    public Boolean getNewPasswordRequired() {
        return newPasswordRequired;
    }

    public void setNewPasswordRequired(Boolean newPasswordRequired) {
        this.newPasswordRequired = newPasswordRequired;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
