package com.goshop.common;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

public class DynamoDbClientFactory {
    public static DynamoDbClient createDynamoDbClient() {
        return DynamoDbClient.builder().region(Region.AP_SOUTH_1).build();
    }
}
