package com.bahadirakin.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;

@EnableCaching
@SpringBootApplication
public class SpringRedisApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SpringRedisApplication.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SomeLongBusiness someLongBusiness;

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisApplication.class, args);
    }

    @Bean
    CacheManager cacheManager(StringRedisTemplate stringRedisTemplate) {
        return new RedisCacheManager(stringRedisTemplate);
    }

    @Bean
    SomeLongBusiness someLongBusiness() {
        return new SomeLongBusinessImpl();
    }


    @Override
    public void run(String... strings) throws Exception {

        // Messaging Demonstration
        logger.info("Sending message.....");
        stringRedisTemplate.opsForValue().set("message", "Hello, World");
        final String message = stringRedisTemplate.opsForValue().get("message");
        logger.info("Message From Redis Server {}", message);

        // Caching Demonstration
        logger.info("Heavy work is starting for the first time");
        final String firstResult = someLongBusiness.doSomeHeavyWork(message);
        logger.info("First (Takes 5 sec): {}", firstResult);
        final String secondResult = someLongBusiness.doSomeHeavyWork(message);
        logger.info("Second: {}", secondResult);
        final String thirdResult = someLongBusiness.doSomeHeavyWork("Different Message");
        logger.info("Third (Takes 5 sec): {}", thirdResult);
        final String forthResult = someLongBusiness.doSomeHeavyWork("Different Message");
        logger.info("Forth: {}", forthResult);
    }
}
