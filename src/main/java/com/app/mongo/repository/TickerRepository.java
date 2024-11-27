package com.app.mongo.repository;

import com.app.mongo.entities.TickerMetaDataMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TickerRepository extends MongoRepository<TickerMetaDataMongo,String> {
}
