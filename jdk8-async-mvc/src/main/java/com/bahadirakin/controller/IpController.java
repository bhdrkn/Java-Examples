package com.bahadirakin.controller;

import com.bahadirakin.model.Ip;
import com.bahadirakin.service.HttpBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping(path = "/ip")
public class IpController {

    @Autowired
    HttpBinService httpBinService;

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<Ip>> ip() {
        return httpBinService.ip()
                .thenApply(ResponseEntity::ok);
    }
}
