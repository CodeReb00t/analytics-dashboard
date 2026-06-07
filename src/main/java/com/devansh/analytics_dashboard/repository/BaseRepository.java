package com.devansh.analytics_dashboard.repository;

import org.springframework.data.mongodb.core.MongoTemplate;

public abstract class BaseRepository<T> {

    protected final MongoTemplate mongoTemplate;

    protected BaseRepository(
            MongoTemplate mongoTemplate
    ) {
        this.mongoTemplate
                = mongoTemplate;
    }

    public T save(
            T entity
    ) {
        return mongoTemplate.save(
                entity
        );
    }
}
