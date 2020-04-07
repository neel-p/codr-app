package com.codr.aws.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApiResponse {

    @JsonIgnore //don't send over network
    private boolean error = false;

    private Map<String, Object> response = new HashMap<>();

    public void add(String key, Object value) {
        response.put(key, value);
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isError() {
        return error;
    }

    public Map<String, Object> getResponse() {
        return Collections.unmodifiableMap(response);
    }
}