package com.beergode.decisionmaker.adapters.survey.mongo;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.AnswerEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.QuestionEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.repository.SurveyMongoRepository;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyCreatePort;
import com.beergode.decisionmaker.survey.usecase.AnswerCreate;
import com.beergode.decisionmaker.survey.usecase.QuestionCreate;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyCreateDataAdapter implements SurveyCreatePort {

    private final SurveyMongoRepository surveyMongoRepository;

    @Override
    public Survey create(SurveyCreate surveyCreate) {
        var surveyEntity = new SurveyEntity();
        surveyEntity.setContent(surveyCreate.getContent());
//        QuestionCreate question = surveyCreate.getQuestion();
//        QuestionEntity questionEntity = new QuestionEntity();
//        questionEntity.setText(question.getText());
//        List<AnswerEntity> answers = question.getAnswerList().stream().map(answerCreate -> {
//            AnswerEntity answerEntity = new AnswerEntity();
//            answerEntity.setText(answerEntity.getText());
//            return answerEntity;
//        }).toList();
//        questionEntity.setAnswers(answers);
//        surveyEntity.setQuestion(questionEntity);
        return surveyMongoRepository.save(surveyEntity).toModel();
    }
}
