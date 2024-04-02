package com.beergode.decisionmaker.adapters.survey.mongo.repository;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SurveyMongoRepository extends MongoRepository<SurveyEntity, String> {
    Optional<SurveyEntity> findByHandlingKey(String handlingKey);
}
