package com.app.kafka.service.listener;

import com.app.mongo.service.TreatmentOfDataService;
import com.app.kafka.utils.schema.tickersLastOpp.Body;
import com.app.kafka.utils.schema.tickersLastOpp.TickersLastOpp;
import com.app.mongo.entities.TickersLastOppMongo;
import com.app.mongo.service.TickerLastOppService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TickersLastOppConsumer {

    private final TickerLastOppService tickerLastOppService;
    private final TreatmentOfDataService treatmentOfDataService;
    private final ObjectMapper objectMapper;

    public TickersLastOppConsumer(TickerLastOppService studentService, TreatmentOfDataService treatmentOfDataService, ObjectMapper objectMapper) {
        this.tickerLastOppService = studentService;
        this.treatmentOfDataService = treatmentOfDataService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "tickers_last_opp", groupId = "tickers_last_opp", containerFactory = "kafkaListenerLastOppContainerFactory")
    public void consume(TickersLastOpp tickers) {

        TickersLastOpp tickersLastOpp = objectMapper.convertValue(tickers, TickersLastOpp.class);
        List<Body> bodies = tickersLastOpp.getBody();

        for(Body body : bodies){

            TickersLastOppMongo tickersLastOppMongo = new TickersLastOppMongo();
            tickersLastOppMongo.setName(body.getName());
            tickersLastOppMongo.setSymbol(body.getSymbol());
            tickersLastOppMongo.setNetChange(treatmentOfDataService.convertStringIntoNumberFormat(body.getNetchange()));
            tickersLastOppMongo.setPctchange(treatmentOfDataService.convertStringIntoNumberFormat(body.getPctchange()));
            tickersLastOppMongo.setLastsale(treatmentOfDataService.convertStringIntoNumberFormat(body.getLastsale()));
            tickersLastOppMongo.setCurrency(treatmentOfDataService.extractCurrencyFromInput(body.getLastsale()));
            tickersLastOppMongo.setMarketCap(body.getMarketCap());

            tickerLastOppService.saveLastOpp(tickersLastOppMongo);

        }
    }
}
