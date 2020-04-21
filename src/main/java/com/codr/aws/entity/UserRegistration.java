package com.codr.aws.entity;

import java.util.Objects;

public class UserRegistration {

    public int id;
    public String username;
    public String password;
    public String email;
    public String user_pool_id;
    public String user_pool_client_id;
    public String user_group_id;

    public UserRegistration() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_pool_id() {
        return user_pool_id;
    }

    public void setUser_pool_id(String user_pool_id) {
        this.user_pool_id = user_pool_id;
    }

    public String getUser_pool_client_id() {
        return user_pool_client_id;
    }

    public void setUser_pool_client_id(String user_pool_client_id) {
        this.user_pool_client_id = user_pool_client_id;
    }

    public String getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(String user_group_id) {
        this.user_group_id = user_group_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistration that = (UserRegistration) o;
        return id == that.id &&
                username.equals(that.username) &&
                password.equals(that.password) &&
                email.equals(that.email) &&
                user_pool_id.equals(that.user_pool_id) &&
                user_pool_client_id.equals(that.user_pool_client_id) &&
                user_group_id.equals(that.user_group_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, user_pool_id, user_pool_client_id, user_group_id);
    }

    @Override
    public String toString() {
        return "UserRegistration{" +
                "id=" + id +
                ", username=" + username +
                ", password=" + password +
                ", email=" + email +
                ", user_pool_id=" + user_pool_id +
                ", user_pool_client_id=" + user_pool_client_id +
                ", user_group_id=" + user_group_id +
                '}';
    }
}
