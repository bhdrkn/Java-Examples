package com.bahadirakin.redis;

import org.springframework.cache.annotation.Cacheable;

public class SomeLongBusinessImpl implements SomeLongBusiness {

    @Cacheable(value = "resultCache2")
    @Override
    public String doSomeHeavyWork(String input) {
        try {
            Thread.sleep(5000);
            return "Result: " + input;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
