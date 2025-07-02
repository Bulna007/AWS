package com.goshop.dao;

import com.google.common.collect.ImmutableMap;
import com.goshop.common.DynamoDbClientFactory;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

public class ProductDao {

    public void saveProduct(final ImmutableMap<String, String> collection) {

        DynamoDbClient dynamoDbClient = DynamoDbClientFactory.createDynamoDbClient();
        final Map<String, AttributeValue> item = new HashMap<>();

        collection.entrySet().stream().forEach((entry) -> {
            item.put(entry.getKey(), AttributeValue.builder().s(entry.getValue()).build());
        });


        PutItemRequest request = PutItemRequest.builder()
                .tableName("product")
                .item(item)
                .build();
        dynamoDbClient.putItem(request);
    }

    public Map<String, String> getProduct(String productNo) {
        DynamoDbClient dynamoDbClient = DynamoDbClientFactory.createDynamoDbClient();

        final ImmutableMap<String, AttributeValue> partitionKey = ImmutableMap
                .of("product_no", AttributeValue.builder().s(productNo).build());

        final GetItemRequest getItemRequest = GetItemRequest.builder()
                .tableName("product")
                .key(partitionKey).build();
        final Map<String, AttributeValue> returnItem = dynamoDbClient.getItem(getItemRequest).item();

        final Map<String, String> productItem = new HashMap<>();
        returnItem.entrySet().forEach(entry -> {
            productItem.put(entry.getKey(), entry.getValue().s());
        });

        return productItem;
    }
}
