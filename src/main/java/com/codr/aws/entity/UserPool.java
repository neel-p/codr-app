package com.codr.aws.entity;

import java.util.Objects;

public class UserPool {

    public int id;
    public String name;
    public String pool_id;
    public String pool_arn;
    public String client_id;

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

    public String getPool_id() {
        return pool_id;
    }

    public void setPool_id(String pool_id) {
        this.pool_id = pool_id;
    }

    public String getPool_arn() {
        return pool_arn;
    }

    public void setPool_arn(String pool_arn) {
        this.pool_arn = pool_arn;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public UserPool() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPool userPool = (UserPool) o;
        return id == userPool.id &&
                name.equals(userPool.name) &&
                pool_id.equals(userPool.pool_id) &&
                pool_arn.equals(userPool.pool_arn) &&
                client_id.equals(userPool.client_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pool_id, pool_arn, client_id);
    }

    @Override
    public String toString() {
        return "UserPool{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pool_id='" + pool_id + '\'' +
                ", pool_arn='" + pool_arn + '\'' +
                ", client_id='" + client_id + '\'' +
                '}';
    }
}
