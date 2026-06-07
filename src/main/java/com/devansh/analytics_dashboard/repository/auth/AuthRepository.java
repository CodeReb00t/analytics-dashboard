package com.devansh.analytics_dashboard.repository.auth;

import java.util.Optional;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.devansh.analytics_dashboard.models.UserModel;
import com.devansh.analytics_dashboard.repository.BaseRepository;


@Repository
public class AuthRepository extends BaseRepository<UserModel>{

    public AuthRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate);
    }

    public Optional<UserModel> findByEmail(String email) {
        Query query = new Query();
        Criteria userCriteria = Criteria.where("email").is(email);
        query.addCriteria(userCriteria);
        UserModel user = mongoTemplate.findOne(query, UserModel.class);
        return Optional.ofNullable(user);
    }

}
