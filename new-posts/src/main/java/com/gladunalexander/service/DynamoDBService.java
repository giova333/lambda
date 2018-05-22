package com.gladunalexander.service;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.gladunalexander.aws.AWSFactory;
import com.gladunalexander.domain.Post;

import java.util.UUID;

/**
 * Created by Alexander Gladun on 22/05/2018.
 */
public class DynamoDBService {

    private static final String DYNAMO_DB_TABLE_NAME = "posts";

    private final DynamoDB dynamoDB;

    public DynamoDBService() {
        this.dynamoDB = AWSFactory.getDynamoDB();
    }

    public Post createPost(Post post) {
        String postId = UUID.randomUUID().toString();
        post.setId(postId);

        dynamoDB.getTable(DYNAMO_DB_TABLE_NAME).putItem(
                new PutItemSpec().withItem(new Item()
                        .withString("id", post.getId())
                        .withString("voice", post.getVoice())
                        .withString("text", post.getText())));
        return post;
    }
}
