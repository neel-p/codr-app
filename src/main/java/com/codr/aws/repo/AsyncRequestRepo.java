package com.codr.aws.repo;

import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.codr.aws.enums.AsyncRequestResourceType;
import com.codr.aws.dto.AsyncRequestStatus;

import java.util.Optional;

public interface AsyncRequestRepo {

    String logRequest(String requestGuid, PutRecordResult result, String payload, AsyncRequestResourceType rt);

    Optional<AsyncRequestStatus> getStatus(String guid);

}
