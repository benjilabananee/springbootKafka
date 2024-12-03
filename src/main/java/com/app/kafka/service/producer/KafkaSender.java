package com.app.kafka.service.producer;

import com.app.utils.tickers.TickersMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    private static final String TOPIC = "tickerMetaData";

    @Autowired
    private KafkaTemplate<String, TickersMetaData> kafkaTemplate;

    public void sendMessage(TickersMetaData tickersMetaData) {
        kafkaTemplate.send(TOPIC, tickersMetaData);
        System.out.println("Message sent: " + tickersMetaData);
    }
}
