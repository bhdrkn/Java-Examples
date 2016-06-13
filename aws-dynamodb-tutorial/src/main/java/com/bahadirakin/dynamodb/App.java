package com.bahadirakin.dynamodb;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.bahadirakin.dynamodb.dao.UserRepository;
import com.bahadirakin.dynamodb.dao.UserRepositoryImpl;
import com.bahadirakin.dynamodb.model.User;

import java.io.InputStream;
import java.util.List;

/**
 * Application to test behavior.
 */
public final class App {

    private App() {
    }

    /**
     * Main method for application.
     */
    //CHECKSTYLE.OFF: AbbreviationAsWordInName
    public static void main(String[] args) throws Exception {
        final InputStream properties = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("aws.properties");
        final AWSCredentials propertiesCredentials = new PropertiesCredentials(properties);

        final AmazonDynamoDB amazonDynamoDb = new AmazonDynamoDBClient(propertiesCredentials);
        amazonDynamoDb.setRegion(Region.getRegion(Regions.US_WEST_2));
        final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDb);
        final UserRepository userRepository = new UserRepositoryImpl(dynamoDBMapper);

        // Save
        final User user = new User();
        user.setUsername("bhdrkn");
        user.setEmail("bhdrkn@gmail.com");
        user.setPassword("1q2w3e");
        userRepository.put(user);

        // Read
        final User readUser = userRepository.get("bhdrkn");
        assert user.equals(readUser);

        // Read All
        final List<User> users = userRepository.findAll();
        assert users.contains(user);


        // Find by email
        final List<User> findUsers = userRepository.findByEmail("bhdrkn@gmail.com");
        assert findUsers.contains(user);

        // Find by email
        final List<User> notFound = userRepository.findByEmail("asdf");
        assert notFound.isEmpty();

        // Delete
        userRepository.delete("bhdrkn");
        final List<User> all = userRepository.findAll();
        assert all.isEmpty();

        System.out.println("Finished!");
    }
}
