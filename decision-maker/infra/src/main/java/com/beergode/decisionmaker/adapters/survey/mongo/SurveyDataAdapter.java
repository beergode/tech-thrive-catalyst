package com.beergode.decisionmaker.adapters.survey.mongo;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.AnswerEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.QuestionEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.repository.SurveyMongoRepository;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyDataAdapter implements SurveyPort {

    private final SurveyMongoRepository surveyMongoRepository;

    @Override
    public Survey create(SurveyCreate surveyCreate) {
        var question = surveyCreate.getQuestion();
        var answers = question.getAnswers()
                .stream()
                .map(answerCreate -> AnswerEntity.of(answerCreate.getStringId(), answerCreate.getText()))
                .toList();
        var questionEntity = QuestionEntity.of(question.getStringId(), question.getText(), answers);
        var surveyEntity = SurveyEntity.of(surveyCreate.getStringId(), surveyCreate.getContent(), questionEntity);

        return surveyMongoRepository.save(surveyEntity)
                .toModel();
    }

    @Override
    public void update(SurveyUpdate surveyUpdate) {
        var question = surveyUpdate.getQuestion();
        var answers = question.getAnswers()
                .stream()
                .map(answerUpdate ->
                        AnswerEntity.of(answerUpdate.getStringId(), answerUpdate.getText(),
                                answerUpdate.getVoteCount()))
                .toList();
        var questionEntity = QuestionEntity.of(question.getStringId(), question.getText(), answers);
        var surveyEntity = SurveyEntity.of(surveyUpdate.getStringId(), surveyUpdate.getContent(), questionEntity);

        surveyMongoRepository.save(surveyEntity);
    }

    @Override
    public Survey retrieve(String id) {
        return surveyMongoRepository.findById(id).orElseThrow(null).toModel();
    }

    @Override
    public Page<Survey> paginate(SurveyPaginate surveyPaginate) {
        var pageRequest = PageRequest.of(surveyPaginate.getPage().getPageNumber(), surveyPaginate.getPage().getSize());
        var surveyPage = surveyMongoRepository.findAll(pageRequest);
        var surveys = surveyPage.stream().map(SurveyEntity::toModel).toList();
        return Page.of(surveys, surveyPage.getNumber(), surveyPage.getSize(), surveyPage.getTotalElements());
    }
}
