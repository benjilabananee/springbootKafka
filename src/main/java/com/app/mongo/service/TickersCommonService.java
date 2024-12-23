package com.app.mongo.service;

import com.app.DTO.TickerLastOppAggregatedDTO;
import com.app.mongo.entities.TickersLastOppMongo;
import com.app.mongo.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TickersCommonService {

    private final MongoTemplate mongoTemplate;
    private final TickerRepository tickerRepository;

    public TickersCommonService(MongoTemplate mongoTemplate, TickerRepository tickerRepository) {
        this.mongoTemplate = mongoTemplate;
        this.tickerRepository = tickerRepository;
    }

    public List<TickerLastOppAggregatedDTO> getTickersWithInfo() {

        try {
            Aggregation aggregation = Aggregation.newAggregation(
                    Aggregation.lookup(
                            "tickerMetaDataMongo", // from
                            "_id",                // localField
                            "_id",                // foreignField
                            "tickerInfo"          // as
                    ),
                    Aggregation.match(
                            Criteria.where("tickerInfo").ne(List.of()) //exlude empty array
                    )
            );

            AggregationResults<TickerLastOppAggregatedDTO> results = mongoTemplate.aggregate(
                    aggregation,
                    "tickersLastOppMongo", // Input collection
                    TickerLastOppAggregatedDTO.class // Output class
            );

            return results.getMappedResults();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveLastOpp(TickersLastOppMongo tickerLastOppMongo) {
        try {
            tickerRepository.save(tickerLastOppMongo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<TickersLastOppMongo> getTickersByLastSaleGreaterThanEqual(Double minLastSale) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("lastsale").gte(minLastSale));

            return mongoTemplate.find(query, TickersLastOppMongo.class, "tickersLastOppMongo");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
