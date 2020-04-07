package com.codr.aws.enums;

import java.util.stream.Stream;

public enum AsyncRequestStatus {
    UNKNOWN(0),
    POSTED(1),
    IN_PROGRESS(2),
    PROCESSED(3)
    ;

    private final int status;
    AsyncRequestStatus(int status) {
        this.status = status;
    }

    public static AsyncRequestStatus get(int status) {
        return Stream.of(AsyncRequestStatus.values())
                .filter(s -> s.status == status)
                .findFirst()
                .orElse(AsyncRequestStatus.UNKNOWN);
    }

    public int val() {
        return status;
    }

}
