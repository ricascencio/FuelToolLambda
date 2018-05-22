package com.amazonaws.lambda.manager;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDBManager {

    private static volatile DynamoDBManager instance;

    private static AmazonDynamoDB mapper;

    private DynamoDBManager() {
    	mapper = AmazonDynamoDBClientBuilder.defaultClient();        
    }

    public static DynamoDBManager instance() {

        if (instance == null) {
            synchronized(DynamoDBManager.class) {
                if (instance == null)
                    instance = new DynamoDBManager();
            }
        }

        return instance;
    }

    public static AmazonDynamoDB mapper() {

        DynamoDBManager manager = instance();
        return manager.mapper;
    }

}