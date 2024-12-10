package com.app.mongo.service;

import com.app.mongo.entities.TickersLastOppMongo;
import com.app.mongo.repository.TickerRepository;
import org.springframework.stereotype.Service;

@Service
public class TickerLastOppService {

    private final TickerRepository tickerRepository;

    public TickerLastOppService(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    public void saveLastOpp(TickersLastOppMongo studentMongo) {
        tickerRepository.save(studentMongo);
    }

}