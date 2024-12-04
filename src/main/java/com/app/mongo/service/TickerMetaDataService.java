package com.app.mongo.service;

import com.app.mongo.entities.TickerMetaDataMongo;
import com.app.mongo.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TickerMetaDataService {

    private final TickerRepository tickerRepository;

    public TickerMetaDataService(TickerRepository tickerRepository) {
        this.tickerRepository = tickerRepository;
    }

    public void saveData(TickerMetaDataMongo studentMongo) {
        tickerRepository.save(studentMongo);
    }

}