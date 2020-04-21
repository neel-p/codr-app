package com.codr.aws.entity;

import java.util.Objects;

public class UserPoolClient {

    public int id;
    public String pool_id;
    public String client_id;
    public String client_name;
    public String client_secret_key;

    public UserPoolClient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_secret_key() {
        return client_secret_key;
    }

    public void setClient_secret_key(String client_secret_key) {
        this.client_secret_key = client_secret_key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPoolClient that = (UserPoolClient) o;
        return id == that.id &&
                pool_id.equals(that.pool_id) &&
                client_id.equals(that.client_id) &&
                client_name.equals(that.client_name) &&
                client_secret_key.equals(that.client_secret_key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pool_id, client_id, client_name, client_secret_key);
    }

    @Override
    public String toString() {
        return "UserPoolClient{" +
                "id=" + id +
                ", pool_id='" + pool_id + '\'' +
                ", client_id='" + client_id + '\'' +
                ", client_name='" + client_name + '\'' +
                ", client_secret_key='" + client_secret_key + '\'' +
                '}';
    }
}
