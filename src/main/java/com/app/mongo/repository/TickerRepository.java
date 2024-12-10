package com.app.mongo.repository;

import com.app.mongo.entities.TickersLastOppMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TickerRepository extends MongoRepository<TickersLastOppMongo,String> {
}
