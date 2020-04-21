package com.codr.aws.resource;

import com.amazonaws.regions.Regions;

public interface CognitoResources {

    public final static String LOCATION = "custom:location"; //""custom:location";
    public final static String EMAIL = "email";
    // Cognito IAM ID for full Cognito access
    public final static String cognitoID = ""; // ""Your Cognito IAM ID goes here";
    // Cognito IAM "secret key" for full Cognito access
    public final static String cognitoKey = "";  //""Your Cognito IAM secret key goes here";
    public final static String poolID = ""; //""Your Cognito Pool ID goes here";
    public final static String clientID = ""; //""Your Cognito client ID goes here";
    // Replace this with the AWS region for your application
    public final static Regions region = Regions.US_WEST_2;
    // Your Arbitrator Role ARN goes here.
    public final static String ARBITRATOR_ROLE_ARN = "";
    // Your Super Arbitrator Role ARN goes here.
    public final static String SUPER_ARBITRATOR_ROLE_ARN = "";

}
