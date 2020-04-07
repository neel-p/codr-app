package com.codr.aws.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @JsonProperty("guid")
    private String guid;

    @NotNull(message = "Enter valid first name(3-127)")
    @NotEmpty(message = "Enter valid first name(3-127)")
    @Size(min = 3, max = 127, message = "Enter valid first name(3-127)")
    @JsonProperty("firstName")
    private String firstName;

    @NotNull(message = "Enter valid last name(3-127)")
    @NotEmpty(message = "Enter valid last name(3-127)")
    @Size(min = 3, max = 127, message = "Enter valid last name(3-127)")
    @JsonProperty("lastName")
    private String lastName;

    @NotNull(message = "Enter valid contact(3-127)")
    @NotEmpty(message = "Enter valid contact(3-127)")
    @Size(message = "Enter valid contact(3-127)", min = 3, max = 127)
    @JsonProperty("contact")
    private String contact;

    public User(String guid, String firstName, String lastName, String contact) {
        this.guid = guid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
    }

    public User() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return guid.equals(user.guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("guid='").append(guid).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", contact='").append(contact).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
