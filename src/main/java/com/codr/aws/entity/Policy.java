package com.codr.aws.entity;

import java.util.Objects;

public class Policy {

    public int id;
    public String name;
    public String description;
    public String document;
    public String res_policy_arn;

    public Policy() {
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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getRes_policy_arn() {
        return res_policy_arn;
    }

    public void setRes_policy_arn(String res_policy_arn) {
        this.res_policy_arn = res_policy_arn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return id == policy.id &&
                name.equals(policy.name) &&
                description.equals(policy.description) &&
                document.equals(policy.document) &&
                res_policy_arn.equals(policy.res_policy_arn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, document, res_policy_arn);
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", document='" + document + '\'' +
                ", res_policy_arn='" + res_policy_arn + '\'' +
                '}';
    }
}
