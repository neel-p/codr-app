package com.codr.aws.entity;

import java.util.Objects;

public class UserGroup {

    public int id;
    public String name;
    public String description;
    public String precedence;
    public String role_arn;

    public UserGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrecedence() {
        return precedence;
    }

    public void setPrecedence(String precedence) {
        this.precedence = precedence;
    }

    public String getRole_arn() {
        return role_arn;
    }

    public void setRole_arn(String role_arn) {
        this.role_arn = role_arn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGroup userGroup = (UserGroup) o;
        return id == userGroup.id &&
                name.equals(userGroup.name) &&
                description.equals(userGroup.description) &&
                precedence.equals(userGroup.precedence) &&
                role_arn.equals(userGroup.role_arn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, precedence, role_arn);
    }

    @Override
    public String toString() {
        return "UserGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", precedence='" + precedence + '\'' +
                ", role_arn='" + role_arn + '\'' +
                '}';
    }
}
