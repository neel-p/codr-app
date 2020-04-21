package com.codr.aws.entity;

import java.util.Objects;

public class Role {

    public int id;
    public String name;
    public String description;
    public String res_role_arn;
    public String res_role_id;
    public String res_role_policy_document;


    public Role() {
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

    public String getRes_role_arn() {
        return res_role_arn;
    }

    public void setRes_role_arn(String res_role_arn) {
        this.res_role_arn = res_role_arn;
    }

    public String getRes_role_id() {
        return res_role_id;
    }

    public void setRes_role_id(String res_role_id) {
        this.res_role_id = res_role_id;
    }

    public String getRes_role_policy_document() {
        return res_role_policy_document;
    }

    public void setRes_role_policy_document(String res_role_policy_document) {
        this.res_role_policy_document = res_role_policy_document;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                name.equals(role.name) &&
                description.equals(role.description) &&
                res_role_arn.equals(role.res_role_arn) &&
                res_role_id.equals(role.res_role_id) &&
                res_role_policy_document.equals(role.res_role_policy_document);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, res_role_arn, res_role_id, res_role_policy_document);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", res_role_arn='" + res_role_arn + '\'' +
                ", res_role_id='" + res_role_id + '\'' +
                ", res_role_policy_document='" + res_role_policy_document + '\'' +
                '}';
    }
}
