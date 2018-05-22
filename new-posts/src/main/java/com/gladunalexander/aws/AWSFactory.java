package com.gladunalexander.aws;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.sns.AmazonSNSAsyncClient;

/**
 * Created by Alexander Gladun on 20/05/2018.
 */
public class AWSFactory {

    private static final Regions REGION = Regions.US_EAST_1;

    private static DynamoDB dynamoDB;

    private static AmazonSNSAsyncClient snsAsyncClient;

    static {
        initDynamoDbClient();
        initSnsClient();
    }

    public static DynamoDB getDynamoDB() {
        return dynamoDB;
    }

    public static AmazonSNSAsyncClient getSnsAsyncClient() {
        return snsAsyncClient;
    }

    private static void initSnsClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        dynamoDB = new DynamoDB(client);
    }

    private static void initDynamoDbClient() {
        snsAsyncClient = new AmazonSNSAsyncClient();
        snsAsyncClient.setRegion(Region.getRegion(REGION));
    }
}
