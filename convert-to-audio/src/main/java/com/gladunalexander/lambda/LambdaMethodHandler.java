package com.gladunalexander.lambda;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.gladunalexander.service.DynamoDBService;
import com.gladunalexander.service.PollyService;
import com.gladunalexander.service.S3Service;
import java.io.File;

/**
 * Created by Alexander Gladun on 19/05/2018.
 */
public class LambdaMethodHandler implements RequestHandler<SNSEvent, String> {

    private final DynamoDBService dynamoDBService = new DynamoDBService();

    private final PollyService pollyService = new PollyService();

    private final S3Service s3Service = new S3Service();


    public String handleRequest(SNSEvent event, Context context) {
        String postId = event.getRecords().get(0).getSNS().getMessage();

        Item item = dynamoDBService.getPost(postId);

        String text = item.getString("text");
        String voice = item.getString("voice");

        File file = pollyService.convertTextToAudioFile(text, voice, postId);

        String fileUrl = s3Service.uploadToBucket(file);

        dynamoDBService.attachAudioLink(postId, fileUrl);
        return fileUrl;
    }
}
