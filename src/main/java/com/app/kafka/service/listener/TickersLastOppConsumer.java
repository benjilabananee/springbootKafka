package com.app.kafka.service.listener;

import com.app.mongo.service.TickersCommonService;
import com.app.mongo.service.TreatmentOfDataService;
import com.app.kafka.utils.schema.tickersLastOpp.Body;
import com.app.kafka.utils.schema.tickersLastOpp.TickersLastOpp;
import com.app.mongo.entities.TickersLastOppMongo;
import com.app.mongo.service.TickerLastOppService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class TickersLastOppConsumer {

    private final TickersCommonService tickersCommonService;
    private final TreatmentOfDataService treatmentOfDataService;
    private static final Logger log = LoggerFactory.getLogger(TickersLastOppConsumer.class);

    public TickersLastOppConsumer(TickersCommonService tickersCommonService, TreatmentOfDataService treatmentOfDataService) {
        this.tickersCommonService = tickersCommonService;
        this.treatmentOfDataService = treatmentOfDataService;
    }

    @KafkaListener(topics = "tickers_last_opp", groupId = "tickers_last_opp", containerFactory = "kafkaListenerLastOppContainerFactory")
    public void consume(TickersLastOpp tickers) {
        try {
            List<Body> bodies = tickers.getBody();

            for (Body body : bodies) {
                try {
                    TickersLastOppMongo tickersLastOppMongo = new TickersLastOppMongo();
                    tickersLastOppMongo.setName(body.getName());
                    tickersLastOppMongo.setSymbol(body.getSymbol());
                    tickersLastOppMongo.setNetChange(treatmentOfDataService.convertStringIntoNumberFormat(body.getNetchange()));
                    tickersLastOppMongo.setPctchange(treatmentOfDataService.convertStringIntoNumberFormat(body.getPctchange()));
                    tickersLastOppMongo.setLastsale(treatmentOfDataService.convertStringIntoNumberFormat(body.getLastsale()));
                    tickersLastOppMongo.setCurrency(treatmentOfDataService.extractCurrencyFromInput(body.getLastsale()));
                    tickersLastOppMongo.setMarketCap(body.getMarketCap());

                    tickersCommonService.saveLastOpp(tickersLastOppMongo);
                } catch (Exception e) {
                    log.error("Error processing body: {}", body, e);
                }
            }
        } catch (Exception e) {
            log.error("Malformed message received: {}", tickers, e);
        }
    }
}
