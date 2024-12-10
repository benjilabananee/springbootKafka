package com.app.mongo.repository;

import com.app.mongo.entities.TickerMetaData;
import com.app.mongo.entities.TickersLastOppMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerMetaDataRepository extends MongoRepository<TickerMetaData,String> {
}
