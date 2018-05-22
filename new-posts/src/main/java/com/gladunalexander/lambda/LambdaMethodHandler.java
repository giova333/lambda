package com.gladunalexander.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.gladunalexander.domain.Post;
import com.gladunalexander.service.DynamoDBService;
import com.gladunalexander.service.SnsService;

/**
 * Created by Alexander Gladun on 19/05/2018.
 */
public class LambdaMethodHandler implements RequestHandler<Post, Post> {

    private final DynamoDBService dynamoDBService = new DynamoDBService();

    private final SnsService snsService = new SnsService();

    public Post handleRequest(Post post, Context context) {
        Post created = dynamoDBService.createPost(post);
        snsService.sendNotification(created.getId());
        return created;
    }
}
