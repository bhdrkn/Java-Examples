package com.bahadirakin;

import com.bahadirakin.service.HttpBinService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Contains rest client configurations.
 */
@Configuration
public class ClientConfiguration {

    @Bean
    HttpBinService httpBinServiceClient(Retrofit retrofit) {
        return retrofit.create(HttpBinService.class);
    }

    @Bean
    Retrofit retrofit(OkHttpClient okHttpClient,
                      ObjectMapper objectMapper,
                      Executor clientCallbackExecutor,
                      @Value("${client.hostaddress}") String hostaddress) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(Java8CallAdapterFactory.create())
                .baseUrl(hostaddress)
                .callbackExecutor(clientCallbackExecutor)
                .build();
    }

    @Bean
    OkHttpClient okHttpClient(@Value("${client.connection.pool.size}") int connectionPoolSize) {
        return new OkHttpClient.Builder()
                .connectionPool(new ConnectionPool(connectionPoolSize, 5, TimeUnit.MINUTES))
                .build();
    }

    @Bean
    Executor clientCallbackExecutor(@Value("${client.executor.pool.size}") int executorPoolSize) {
        return Executors.newFixedThreadPool(executorPoolSize);
    }

}
