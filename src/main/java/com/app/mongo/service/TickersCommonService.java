package com.app.mongo.service;

import com.app.DTO.TickerLastOppAggregatedDTO;
import com.app.mongo.entities.TickersLastOppMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TickersCommonService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TickerLastOppAggregatedDTO> getTickersWithInfo() {
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

        AggregationResults<TickerLastOppAggregatedDTO>  results = mongoTemplate.aggregate(
                aggregation,
                "tickersLastOppMongo", // Input collection
                TickerLastOppAggregatedDTO.class // Output class
        );

        return results.getMappedResults();
    }
}
