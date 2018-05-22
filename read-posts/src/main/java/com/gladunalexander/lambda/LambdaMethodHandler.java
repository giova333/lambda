package com.gladunalexander.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.gladunalexander.DynamoDBService;
import com.gladunalexander.domain.Post;
import com.gladunalexander.domain.PostId;

import java.util.List;

/**
 * Created by Alexander Gladun on 20/05/2018.
 */
public class LambdaMethodHandler implements RequestHandler<PostId, List<Post>> {

    private final DynamoDBService service = new DynamoDBService();

    @Override
    public List<Post> handleRequest(PostId postId, Context context) {
       return service.getPosts(postId);
    }
}
