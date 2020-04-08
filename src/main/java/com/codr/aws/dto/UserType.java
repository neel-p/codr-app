package com.codr.aws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserType {

    @JsonProperty("guid")
    private String guid;

    @NotNull(message = "Enter valid first name(3-127)")
    @NotEmpty(message = "Enter valid first name(3-127)")
    @Size(min = 3, max = 127, message = "Enter valid user type name(3-127)")
    @JsonProperty("user_type_name")
    private String user_type_name;

    @NotNull(message = "Enter valid last name(3-127)")
    @NotEmpty(message = "Enter valid last name(3-127)")
    @Size(min = 3, max = 127, message = "Enter valid user type details(3-127)")
    @JsonProperty("user_type_details")
    private String user_type_details;

    public UserType(String guid, String user_type_name, String user_type_details) {
        this.guid = guid;
        this.user_type_name = user_type_name;
        this.user_type_details = user_type_details;
    }

    public UserType() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserType userType = (UserType) o;
        return guid.equals(userType.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }

    @Override
    public String toString() {
        return "UserType{" +
                "guid='" + guid + '\'' +
                ", user_type_name='" + user_type_name + '\'' +
                ", user_type_details='" + user_type_details + '\'' +
                '}';
    }


    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUser_type_name() {
        return user_type_name;
    }

    public void setUser_type_name(String user_type_name) {
        this.user_type_name = user_type_name;
    }

    public String getUser_type_details() {
        return user_type_details;
    }

    public void setUser_type_details(String user_type_details) {
        this.user_type_details = user_type_details;
    }
}
