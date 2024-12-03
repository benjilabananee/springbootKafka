package com.app.kafka.service.listener;

import com.app.utils.tickers.Body;
import com.app.utils.tickers.TickersMetaData;
import com.app.mongo.entities.TickerMetaDataMongo;
import com.app.mongo.service.TickerMetaDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TickerMetaDataConsumer {

    private final TickerMetaDataService tickerMetaDataService;
    private final ObjectMapper objectMapper;

    public TickerMetaDataConsumer(TickerMetaDataService studentService, ObjectMapper objectMapper) {
        this.tickerMetaDataService = studentService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "tickerMetaData", groupId = "student-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(TickersMetaData tickers) {

        TickersMetaData tickersMetaData = objectMapper.convertValue(tickers, TickersMetaData.class);
        List<Body> bodies = tickersMetaData.getBody();

        for(Body body : bodies){
            tickerMetaDataService.saveData(objectMapper.convertValue(body,TickerMetaDataMongo.class));
        }





    }
}
