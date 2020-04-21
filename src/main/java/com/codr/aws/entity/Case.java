package com.codr.aws.entity;

import java.util.Objects;

public class Case {

    public int id;
    public String name;
    public String description;
    public int user_id;
    public String user_pool_id;

    public Case() {
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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_pool_id() {
        return user_pool_id;
    }

    public void setUser_pool_id(String user_pool_id) {
        this.user_pool_id = user_pool_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return id == aCase.id &&
                user_id == aCase.user_id &&
                name.equals(aCase.name) &&
                description.equals(aCase.description) &&
                user_pool_id.equals(aCase.user_pool_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, user_id, user_pool_id);
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user_id=" + user_id +
                ", user_pool_id='" + user_pool_id + '\'' +
                '}';
    }
}
