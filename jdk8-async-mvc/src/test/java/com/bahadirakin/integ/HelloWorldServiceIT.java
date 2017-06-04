package com.bahadirakin.integ;

import com.bahadirakin.model.Ip;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HelloWorldServiceIT {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:" + System.getProperty("test.server.port", "8080") + "/")
            .client(new OkHttpClient.Builder().build())
            .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper()))
            .addCallAdapterFactory(Java8CallAdapterFactory.create())
            .build();

    HelloWorldService helloWorldService = retrofit.create(HelloWorldService.class);

    @Test
    public void testIp() throws Exception {
        // Given

        // When
        Ip ip = helloWorldService.ip().get();

        // Then
        assertThat(ip, is(new Ip(getPublicIp())));
    }


    interface HelloWorldService {

        @GET("/ip")
        CompletableFuture<Ip> ip();
    }

    public static String getPublicIp() throws Exception {
        URL ipRequest = new URL("http://checkip.amazonaws.com");

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(ipRequest.openStream()))) {
            return in.readLine();
        }
    }
}
