package com.codr.aws.entity;

import java.util.Objects;

public class RoleAttachPolicy {

    public int id;
    public int role_id;
    public int policy_id;

    public RoleAttachPolicy() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(int policy_id) {
        this.policy_id = policy_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleAttachPolicy that = (RoleAttachPolicy) o;
        return id == that.id &&
                role_id == that.role_id &&
                policy_id == that.policy_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role_id, policy_id);
    }

    @Override
    public String toString() {
        return "RoleAttachPolicy{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", policy_id=" + policy_id +
                '}';
    }
}
