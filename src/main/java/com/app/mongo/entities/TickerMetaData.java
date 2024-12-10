package com.app.mongo.entities;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TickerMetaData {
    private String message;

    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
}
