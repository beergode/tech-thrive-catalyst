package com.beergode.decisionmaker.adapters.survey.mongo.repository;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SurveyMongoRepository extends MongoRepository<SurveyDocument, String> {
    Optional<SurveyDocument> findByHandlingKey(String handlingKey);
    void deleteByHandlingKey(String handlingKey);
}
