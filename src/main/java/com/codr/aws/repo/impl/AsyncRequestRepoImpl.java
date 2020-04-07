package com.codr.aws.repo.impl;

import com.amazonaws.services.kinesis.model.PutRecordResult;
import com.codr.aws.enums.AsyncRequestStatus;
import com.codr.aws.enums.AsyncRequestResourceType;
import com.codr.aws.repo.AsyncRequestRepo;
import com.codr.aws.util.AppUtils;
import org.jdbi.v3.core.Jdbi;

import java.util.Optional;

public class AsyncRequestRepoImpl implements AsyncRequestRepo {

    private final Jdbi jdbi;

    public AsyncRequestRepoImpl(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public String logRequest(String reqGuid, PutRecordResult result, String payload, AsyncRequestResourceType rt) {
        return jdbi.withHandle(h -> {
            StringBuilder q = new StringBuilder();
            q.append("insert into async_request_status ");
            q.append("(req_guid, payload, shard_id, seq_no, status, ts_utc, resource_type) ");
            q.append("values(:req_guid, :payload, :shard_id, :seq_no, :status, UTC_TIMESTAMP(),:resource_type)");
            int i = h.createUpdate(q.toString())
                    .bind("req_guid", reqGuid)
                    .bind("payload", payload)
                    .bind("shard_id", result.getShardId())
                    .bind("seq_no", result.getSequenceNumber())
                    .bind("status", AsyncRequestStatus.POSTED.val())
                    .bind("resource_type", rt.type())
                    .execute();
            if(i <= 0) {
                throw new RuntimeException("Unable to log the request information.");
            }
            return reqGuid;
        });
    }

    @Override
    public Optional<com.codr.aws.dto.AsyncRequestStatus> getStatus(String guid) {
        return jdbi.withHandle(h ->
                h.createQuery("select * from async_request_status where req_guid=:guid")
                    .bind("guid", guid)
                    .map((rs, ctx) -> {
                        com.codr.aws.dto.AsyncRequestStatus reqStatus = new com.codr.aws.dto.AsyncRequestStatus();
                        reqStatus.setResourceGuid(rs.getString("resource_guid"));
                        reqStatus.setResourceType(AsyncRequestResourceType.get(rs.getInt("resource_type")));
                        reqStatus.setSeqNo(rs.getString("seq_no"));
                        reqStatus.setShardId(rs.getString("shard_id"));
                        reqStatus.setStatus(AsyncRequestStatus.get(rs.getInt("status")));
                        reqStatus.setTsUtc(AppUtils.parseSQLDateTime(rs.getString("ts_utc")));
                        return reqStatus;
                    })
                    .findFirst());
    }
}
