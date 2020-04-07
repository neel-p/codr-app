package com.codr.aws.util;

import com.amazonaws.util.StringUtils;

/**
 * Common Class for handling of all environment variables.
 */
public class Environment {

    private static final String KINESIS_USER_STREAM = "KINESIS_USER_STREAM_NAME";
    private static final String APP_PROFILE = "PROFILE";
    private static final String DYNAMO_DB_HASH_KEY = "hashKey";
    private static final String DYNAMO_DB_RANGE_KEY = "rangeKey";
    private static final String DYNAMO_DB_TABLE_NAME = "tableName";
    private static final String DYNAMO_DB_CONN_URL = "connUrl";
    private static final String DYNAMO_DB_USER_NAME = "uName";
    private static final String DYNAMO_DB_PWD = "pwd";

    private static String get(String key) {
        String value = System.getenv(key);
        if (StringUtils.isNullOrEmpty(value))
            throw new IllegalArgumentException("Unset environment variable: " + key);
        return value;
    }

    public static String getKinesisUserStream() {
        return get(KINESIS_USER_STREAM);
    }
    public static String getDynamoDbHashKey(){return  get(DYNAMO_DB_HASH_KEY);}
    public static String getDynamoDbRangeKey(){return  get(DYNAMO_DB_RANGE_KEY);}
    public static String getDynamoDbTableName(){return  get(DYNAMO_DB_TABLE_NAME);}
    public static String getDynamoDbConnUrl(){return get(DYNAMO_DB_CONN_URL);}
    public static String getDynamoDbUserName(){return get(DYNAMO_DB_USER_NAME);}
    public static String getDynamoDbPwd(){return get(DYNAMO_DB_PWD);}
}
