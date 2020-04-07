package com.codr.aws.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.codr.aws.util.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBCredentials {
    private static Item cred;
    private static final Logger logger = LoggerFactory.getLogger(DBCredentials.class);

    private static Item getCred() {
        if(cred == null){
            loadCredFromDynamoDB();
        }
        return cred;
    }

    private static void loadCredFromDynamoDB(){
        try {
            AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
            DynamoDB dynamoDB = new DynamoDB(client);
            Table table = dynamoDB.getTable(Environment.getDynamoDbTableName());
            GetItemSpec spec = new GetItemSpec().withPrimaryKey(Environment.getDynamoDbHashKey(),Environment.getDynamoDbHashKey(),Environment.getDynamoDbRangeKey(),Environment.getDynamoDbRangeKey());
            cred = table.getItem(spec);
        }catch (IllegalArgumentException e){
            logger.error("Dynamo DB client is NULL",e);
        }
    }
    public static String getConnUrl(){
        return getCred().getString(Environment.getDynamoDbConnUrl());
    }
    public static String getUserName(){
        return getCred().getString(Environment.getDynamoDbUserName());
    }
    public static String getPassword(){
        return getCred().getString(Environment.getDynamoDbPwd());
    }
}
