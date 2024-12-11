package com.app.mongo.service;

import com.app.mongo.entities.TickerMetaDataMongo;
import com.app.mongo.repository.TickerMetaDataRepository;
import org.springframework.stereotype.Service;

@Service
public class TickerMetadataService {

    private final TickerMetaDataRepository tickerMetaDataRepository;

    public TickerMetadataService(TickerMetaDataRepository tickerMetaDataRepository) {
        this.tickerMetaDataRepository = tickerMetaDataRepository;
    }

    public void saveTickerMetadata(TickerMetaDataMongo tickerMetaDataMongo){
        tickerMetaDataRepository.save(tickerMetaDataMongo);
    }

}
