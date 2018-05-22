package com.gladunalexander.service;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.gladunalexander.aws.AWSFactory;
import com.gladunalexander.domain.Post;
import com.gladunalexander.domain.PostId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alexander Gladun on 22/05/2018.
 */
public class DynamoDBService {

    private static final String ALL = "*";

    private static final String DYNAMO_DB_TABLE_NAME = "posts";

    private final DynamoDB dynamoDB;

    public DynamoDBService() {
        this.dynamoDB = AWSFactory.getDynamoDB();
    }

    public List<Post> getPosts(PostId postId) {
        Table table = dynamoDB.getTable(DYNAMO_DB_TABLE_NAME);
        List<Item> items = new ArrayList<>();
        if (ALL.equals(postId.getId())) {
            table.scan().forEach(items::add);
        } else {
            Item item = table.getItem(new PrimaryKey("id", postId.getId()));
            items.add(item);
        }
        return toPostList(items);
    }

    private List<Post> toPostList(List<Item> list) {
        return list.stream()
                .map(item -> {
                    Post post = new Post();
                    post.setId(item.getString("id"));
                    post.setVoice(item.getString("voice"));
                    post.setText(item.getString("text"));
                    post.setUrl(item.getString("url"));
                    return post;
                })
                .collect(Collectors.toList());
    }
}
