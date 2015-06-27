package com.bahadirakin.amqp.message;

import java.io.Serializable;

public class HelloMessage implements Serializable {

    private String message;
    private String from;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
