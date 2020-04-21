package com.codr.aws.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.amazonaws.util.StringUtils;
import com.codr.aws.dto.UserType;
import com.codr.aws.enums.AsyncRequestResourceType;
import com.codr.aws.exceptions.BadInputException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.codr.aws.dto.AsyncRequestStatus;
import com.codr.aws.repo.AsyncRequestRepo;
import com.codr.aws.util.AppUtils;
import com.codr.aws.util.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

public class AsyncTypeUserService {

    private static final AmazonKinesis AMAZON_KINESIS;
    private static final Logger logger = LoggerFactory.getLogger(AsyncTypeUserService.class);
    private final AsyncRequestRepo repo;

    public AsyncTypeUserService(AsyncRequestRepo repo) {
        this.repo = repo;
    }

    static {
        AMAZON_KINESIS = AmazonKinesisClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    public String postToKinesis(UserType userType) throws JsonProcessingException {
        AppUtils.validate(userType);
        String guid = UUID.randomUUID().toString();
        String message = new ObjectMapper().writeValueAsString(userType);
        logger.info("Serialized user payload: " + message);
        PutRecordResult result = send(guid, message);
        repo.logRequest(guid, result, message, AsyncRequestResourceType.USER_TYPE);
        return guid;
    }

    public AsyncRequestStatus getStatus(String guid) {
        if (StringUtils.isNullOrEmpty(guid)) {
            throw new BadInputException("guid is mandatory");
        }
        Optional<AsyncRequestStatus> status = repo.getStatus(guid);
        if (!status.isPresent()) {
            throw new RuntimeException("Request status not found");
        }
        return status.get();
    }

    private PutRecordResult send(String guid, String message) {
        PutRecordRequest recordRequest = new PutRecordRequest();
        recordRequest.setStreamName(Environment.getKinesisUserStream());
        recordRequest.setPartitionKey(guid);
        recordRequest.withData(ByteBuffer.wrap(message.getBytes()));
        PutRecordResult result = AMAZON_KINESIS.putRecord(recordRequest);
        return result;
    }
}
