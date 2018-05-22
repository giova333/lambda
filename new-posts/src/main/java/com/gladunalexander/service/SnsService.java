package com.gladunalexander.service;

import com.amazonaws.services.sns.AmazonSNSAsyncClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.gladunalexander.aws.AWSFactory;

/**
 * Created by Alexander Gladun on 22/05/2018.
 */
public class SnsService {

    private static final String SNS_TOPIC = "arn:aws:sns:us-east-1:051886884114:new_topics";

    private final AmazonSNSAsyncClient client;

    public SnsService() {
        client = AWSFactory.getSnsAsyncClient();
    }

    public void sendNotification(String message) {
        PublishRequest publishRequest = new PublishRequest(SNS_TOPIC, message);
        client.publish(publishRequest);
    }
}
