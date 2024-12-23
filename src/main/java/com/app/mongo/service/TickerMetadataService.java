package com.app.mongo.service;

import com.app.mongo.entities.TickerMetaDataMongo;
import com.app.mongo.entities.TickersLastOppMongo;
import com.app.mongo.repository.TickerMetaDataRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TickerMetadataService {

    private final TickerMetaDataRepository tickerMetaDataRepository;

    public TickerMetadataService(TickerMetaDataRepository tickerMetaDataRepository, MongoTemplate mongoTemplate) {
        this.tickerMetaDataRepository = tickerMetaDataRepository;
    }

    public void saveTickerMetadata(TickerMetaDataMongo tickerMetaDataMongo){
        tickerMetaDataRepository.save(tickerMetaDataMongo);
    }



}
