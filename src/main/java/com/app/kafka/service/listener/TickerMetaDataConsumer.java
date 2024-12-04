package com.app.kafka.service.listener;

import com.app.mongo.service.TreatmentOfDataService;
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
    private final TreatmentOfDataService treatmentOfDataService;
    private final ObjectMapper objectMapper;

    public TickerMetaDataConsumer(TickerMetaDataService studentService, TreatmentOfDataService treatmentOfDataService, ObjectMapper objectMapper) {
        this.tickerMetaDataService = studentService;
        this.treatmentOfDataService = treatmentOfDataService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "tickerMetaData", groupId = "student-group", containerFactory = "kafkaListenerContainerFactory")
    public void consume(TickersMetaData tickers) {

        TickersMetaData tickersMetaData = objectMapper.convertValue(tickers, TickersMetaData.class);
        List<Body> bodies = tickersMetaData.getBody();

        for(Body body : bodies){

            TickerMetaDataMongo tickerMetaDataMongo = new TickerMetaDataMongo();
            tickerMetaDataMongo.setName(body.getName());
            tickerMetaDataMongo.setSymbol(body.getSymbol());
            tickerMetaDataMongo.setNetChange(treatmentOfDataService.convertStringIntoNumberFormat(body.getNetchange()));
            tickerMetaDataMongo.setPctchange(treatmentOfDataService.convertStringIntoNumberFormat(body.getPctchange()));
            tickerMetaDataMongo.setLastsale(treatmentOfDataService.convertStringIntoNumberFormat(body.getLastsale()));
            tickerMetaDataMongo.setCurrency(treatmentOfDataService.extractCurrencyFromInput(body.getLastsale()));
            tickerMetaDataMongo.setMarketCap(body.getMarketCap());

            tickerMetaDataService.saveData(tickerMetaDataMongo);

        }





    }
}
