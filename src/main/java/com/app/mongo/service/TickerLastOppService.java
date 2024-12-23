package com.app.mongo.service;

import com.app.mongo.entities.TickersLastOppMongo;
import com.app.mongo.repository.TickerRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TickerLastOppService {

    private final TickerRepository tickerRepository;
    private final MongoTemplate mongoTemplate;

    public TickerLastOppService(TickerRepository tickerRepository, MongoTemplate mongoTemplate) {
        this.tickerRepository = tickerRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void saveLastOpp(TickersLastOppMongo studentMongo) {
        tickerRepository.save(studentMongo);
    }

    public List<TickersLastOppMongo> getTickersByLastSaleGreaterThanEqual(Double minLastSale) {

        Query query = new Query();
        query.addCriteria(Criteria.where("lastsale").gte(minLastSale));

        return mongoTemplate.find(query, TickersLastOppMongo.class, "tickersLastOppMongo");
    }

}