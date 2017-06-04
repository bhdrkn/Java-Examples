package com.bahadirakin.service;

import com.bahadirakin.model.Ip;
import retrofit2.http.GET;

import java.util.concurrent.CompletableFuture;

/**
 * A proxy interface for HttpBinService client.
 */
public interface HttpBinService {

    /**
     * Reads Ip object from HttpBin service.
     *
     * @return Ip object that contains client Ip.
     */
    @GET("/ip")
    CompletableFuture<Ip> ip();
}
