package com.example.Journal.App.repository;

import com.example.Journal.App.Entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepo extends MongoRepository<JournalEntity, ObjectId> {

}
