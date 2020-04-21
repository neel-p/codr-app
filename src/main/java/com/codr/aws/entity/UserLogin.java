package com.codr.aws.entity;

import java.util.Objects;

public class UserLogin {

    public int id;
    public String username;
    public String pool_id;
    public String client_id;
    public String access_token;

    public UserLogin() {
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

    public String getPool_id() {
        return pool_id;
    }

    public void setPool_id(String pool_id) {
        this.pool_id = pool_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogin userLogin = (UserLogin) o;
        return id == userLogin.id &&
                username.equals(userLogin.username) &&
                pool_id.equals(userLogin.pool_id) &&
                client_id.equals(userLogin.client_id) &&
                access_token.equals(userLogin.access_token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, pool_id, client_id, access_token);
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pool_id='" + pool_id + '\'' +
                ", client_id='" + client_id + '\'' +
                ", access_token='" + access_token + '\'' +
                '}';
    }
}
