package com.app.mongo.repository;

import com.app.mongo.entities.StudentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<StudentMongo,String> {
}
