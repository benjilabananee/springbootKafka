package com.app.kafka.service.producer;

import com.app.kafka.utils.schema.tickersLastOpp.TickersLastOpp;
import com.app.kafka.utils.schema.tickersMetaData.TickersMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    private static final String TOPIC = "tickers_last_opp";

    @Autowired
    private KafkaTemplate<String, TickersLastOpp> kafkaTickersLastOppTemplate;
    @Autowired
    private KafkaTemplate<String, TickersMetadata> kafkaTickerMetadataTemplate;

    public void sendTickerLastOppMessage(TickersLastOpp tickersLastOpp) {
        kafkaTickersLastOppTemplate.send(TOPIC, tickersLastOpp);
        System.out.println("Message sent: " + tickersLastOpp);
    }


    public void sendTickerMetaDataOppMessage(TickersMetadata tickersMetadata) {
        kafkaTickerMetadataTemplate.send("METADATA", tickersMetadata);
        System.out.println("Message sent: " + tickersMetadata);
    }


}
