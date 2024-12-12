package com.app.kafka.service.listener;


import com.app.kafka.utils.schema.tickersMetaData.Body;
import com.app.kafka.utils.schema.tickersMetaData.TickersMetadata;
import com.app.mongo.service.TickerMetadataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.app.mongo.entities.TickerMetaDataMongo;

import java.util.List;

@Service
public class TickersMetadataConsumer {

    private final TickerMetadataService tickerMetadataService;
    private final ObjectMapper objectMapper;

    public TickersMetadataConsumer(TickerMetadataService tickerMetadataService, ObjectMapper objectMapper) {
        this.tickerMetadataService = tickerMetadataService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "METADATA", groupId = "meta_data_group", containerFactory = "metadataTickerKafkaListenerContainerFactory")
    public void consume(TickersMetadata tickersMetadata){

        List<Body> bodies = tickersMetadata.getBody();

        for(Body body : bodies){
            tickerMetadataService.saveTickerMetadata(objectMapper.convertValue(body, TickerMetaDataMongo.class));
        }


        System.out.println(bodies.get(0).getSymbol() + "!!!!!!!!!!!!!!");
    }
}
