package com.gladunalexander.aws;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

/**
 * Created by Alexander Gladun on 20/05/2018.
 */
public class AWSFactory {

    private static final Regions REGION = Regions.US_EAST_1;

    private static DynamoDB dynamoDB;

    static {
        initDynamoDbClient();
    }

    public static DynamoDB getDynamoDB() {
        return dynamoDB;
    }

    private static void initDynamoDbClient() {
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(REGION));
        dynamoDB = new DynamoDB(client);
    }
}
