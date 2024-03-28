package com.beergode.decisionmaker.adapters.survey.mongo;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.repository.SurveyMongoRepository;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyCreatePort;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyCreateDataAdapter implements SurveyCreatePort {

    private final SurveyMongoRepository surveyMongoRepository;

    @Override
    public Survey create(SurveyCreate surveyCreate) {
//        var question = surveyCreate.getQuestion();
//        var answers = question.getAnswerList()
//                .stream()
//                .map(answerCreate -> AnswerEntity.of(answerCreate.getText()))
//                .toList();
//        var questionEntity = QuestionEntity.of(question.getText(), answers);
        var surveyEntity = SurveyEntity.of(surveyCreate.getContent(), /*questionEntity*/ null);

        return surveyMongoRepository.save(surveyEntity)
                .toModel();
    }
}
