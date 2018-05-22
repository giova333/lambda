package com.gladunalexander.service;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.NameMap;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.gladunalexander.aws.AWSFactory;

/**
 * Created by Alexander Gladun on 22/05/2018.
 */
public class DynamoDBService {

    private static final String DYNAMO_DB_TABLE_NAME = "posts";

    private final Table table;

    public DynamoDBService() {
        table = AWSFactory.getDynamoDb().getTable(DYNAMO_DB_TABLE_NAME);
    }

    public Item getPost(String postId) {
        return table.getItem(new PrimaryKey("id", postId));
    }

    public void attachAudioLink(String postId, String link) {
        UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("id", postId)
                .withUpdateExpression("SET #urlAtt = :urlValue")
                .withNameMap(new NameMap().with("#urlAtt", "url"))
                .withValueMap(new ValueMap().withString(":urlValue", link));
        table.updateItem(updateItemSpec);
    }
}
