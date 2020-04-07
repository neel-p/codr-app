package com.codr.aws.dto;

import com.codr.aws.enums.AsyncRequestResourceType;

import java.util.Date;

public class AsyncRequestStatus {

    private com.codr.aws.enums.AsyncRequestStatus status;
    private Date tsUtc;
    private String shardId;
    private String seqNo;
    private String resourceGuid;
    private AsyncRequestResourceType resourceType;

    public com.codr.aws.enums.AsyncRequestStatus getStatus() {
        return status;
    }

    public void setStatus(com.codr.aws.enums.AsyncRequestStatus status) {
        this.status = status;
    }

    public Date getTsUtc() {
        return tsUtc;
    }

    public void setTsUtc(Date tsUtc) {
        this.tsUtc = tsUtc;
    }

    public String getShardId() {
        return shardId;
    }

    public void setShardId(String shardId) {
        this.shardId = shardId;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getResourceGuid() {
        return resourceGuid;
    }

    public void setResourceGuid(String resourceGuid) {
        this.resourceGuid = resourceGuid;
    }

    public AsyncRequestResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(AsyncRequestResourceType resourceType) {
        this.resourceType = resourceType;
    }
}
