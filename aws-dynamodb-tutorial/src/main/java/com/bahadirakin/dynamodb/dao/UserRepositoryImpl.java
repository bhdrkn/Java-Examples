package com.bahadirakin.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.bahadirakin.dynamodb.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

//CHECKSTYLE.OFF: AbbreviationAsWordInName
public class UserRepositoryImpl implements UserRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public UserRepositoryImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void put(User user) {
        this.dynamoDBMapper.save(user);
    }

    @Override
    public void delete(String username) {
        final User user = new User();
        user.setUsername(username);
        this.dynamoDBMapper.delete(user);
    }

    @Override
    public User get(String username) throws Exception {
        return Optional.ofNullable(dynamoDBMapper.load(User.class, username))
                .orElseThrow(() -> new Exception(String.format("User for username %s was not found", username)));
    }

    @Override
    public List<User> findAll() {
        return dynamoDBMapper.scan(User.class, new DynamoDBScanExpression());
    }

    @Override
    public List<User> findByEmail(String email) {
        final List<User> users = dynamoDBMapper.scan(User.class,
                new DynamoDBScanExpression().withFilterConditionEntry("email",
                        new Condition().withComparisonOperator(ComparisonOperator.EQ)
                                .withAttributeValueList(Collections.singletonList(new AttributeValue(email)))));
        if (users == null) {
            return Collections.emptyList();
        } else {
            return users;
        }
    }
}
