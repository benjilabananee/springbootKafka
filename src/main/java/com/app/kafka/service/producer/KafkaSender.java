package com.app.kafka.service.producer;

import com.app.kafka.utils.schema.tickersLastOpp.TickersLastOpp;
import com.app.kafka.utils.schema.tickersMetaData.TickersMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {

    private static final String LAST_OPP_TOPIC = "tickers_last_opp";
    private static final String METADATA_TOPIC = "METADATA";

    @Autowired
    private KafkaTemplate<String, TickersLastOpp> kafkaTickersLastOppTemplate;
    @Autowired
    private KafkaTemplate<String, TickersMetadata> kafkaTickerMetadataTemplate;

    public void sendTickerLastOppMessage(TickersLastOpp tickersLastOpp) {
        kafkaTickersLastOppTemplate.send(LAST_OPP_TOPIC, tickersLastOpp);
        System.out.println("Message sent: " + tickersLastOpp);
    }

    public void sendTickerMetaDataOppMessage(TickersMetadata tickersMetadata) {
        kafkaTickerMetadataTemplate.send(METADATA_TOPIC, tickersMetadata);
        System.out.println("Message sent: " + tickersMetadata);
    }
}
