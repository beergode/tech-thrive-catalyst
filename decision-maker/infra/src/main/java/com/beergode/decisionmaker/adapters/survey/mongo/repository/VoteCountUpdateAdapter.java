package com.beergode.decisionmaker.adapters.survey.mongo.repository;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.AnswerEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.QuestionEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyEntity;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyCreatePort;
import com.beergode.decisionmaker.survey.port.VoteCountUpdatePort;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.VoteCountUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteCountUpdateAdapter implements VoteCountUpdatePort {

    private final SurveyMongoRepository surveyMongoRepository;

    @Override
    public void voteCountUpdate(VoteCountUpdate voteCountUpdate) {

//        var question = surveyCreate.getQuestion();
//        var answers = question.getAnswers()
//                .stream()
//                .map(answerCreate -> AnswerEntity.of(answerCreate.getText()))
//                .toList();
//        var questionEntity = QuestionEntity.of(question.getText(), answers);
//        var surveyEntity = SurveyEntity.of(surveyCreate.getContent(), questionEntity);
//
//        return surveyMongoRepository.save(surveyEntity)
//                .toModel();
    }
}
