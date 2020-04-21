package com.codr.aws.enums;

import java.util.stream.Stream;

public enum AsyncRequestResourceType {
    USER_TYPE(1, "/userType/%s")
    ;

    private final int t;
    private final String uri;
    AsyncRequestResourceType(int t, String uri) {
        this.t = t;
        this.uri = uri;
    }

    public int type() {
        return t;
    }
    public String uri() {
        return uri;
    }

    public static AsyncRequestResourceType get(int t) {
        return Stream.of(AsyncRequestResourceType.values())
                .filter(rt -> rt.t == t)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid resource type"));
    }
}
