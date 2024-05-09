package com.beergode.decisionmaker.adapters.survey.mongo;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.AnswerField;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.QuestionField;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyDocument;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveySettingField;
import com.beergode.decisionmaker.adapters.survey.mongo.repository.SurveyMongoRepository;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class SurveyDataAdapter implements SurveyPort {
    private final SurveyMongoRepository surveyMongoRepository;

    public SurveyDataAdapter(SurveyMongoRepository surveyMongoRepository) {
        this.surveyMongoRepository = surveyMongoRepository;
    }

    @Override
    public Survey create(SurveyCreate surveyCreate) {
        var question = surveyCreate.getQuestion();
        var surveySetting = surveyCreate.getSetting();
        List<AnswerField> answers = question.getAnswers().isEmpty()
                ? List.of()
                : question.getAnswers()
                        .stream()
                        .map(answerCreate -> AnswerField.of(answerCreate.getStringId(), answerCreate.getText()))
                        .toList();
        var questionField = QuestionField.of(question.getStringId(), question.getText(), answers);
        var surveySettingField = ofNullable(surveySetting)
                .map(setting -> SurveySettingField.of(setting.getParticipantLimit(), setting.isCustomInputEnabled()))
                .orElse(null);
        var surveyDocument = SurveyDocument.of(surveyCreate.getStringId(),
                surveyCreate.getContent(),
                surveyCreate.getNote(),
                surveyCreate.getCountdownDurationSeconds(),
                questionField,
                surveySettingField,
                surveyCreate.getStringHandlingKey());

        return surveyMongoRepository.save(surveyDocument)
                .toModel();
    }

    @Override
    public Survey update(SurveyUpdate surveyUpdate) {
        var question = surveyUpdate.getQuestion();
        var surveySetting = surveyUpdate.getSetting();
        var answers = question.getAnswers()
                .stream()
                .map(answerUpdate ->
                        AnswerField.of(answerUpdate.getStringId(), answerUpdate.getText(),
                                answerUpdate.getVoteCount()))
                .toList();
        var questionField = QuestionField.of(question.getStringId(), question.getText(), answers);
        var surveySettingField = ofNullable(surveySetting)
                .map(setting -> SurveySettingField.of(setting.getParticipantLimit(), setting.isCustomInputEnabled()))
                .orElse(null);
        var surveyEntity = SurveyDocument.of(surveyUpdate.getStringId(),
                surveyUpdate.getContent(),
                surveyUpdate.getNote(),
                surveyUpdate.getCountdownDurationSeconds(),
                questionField,
                surveyUpdate.getClosedAt(),
                surveySettingField,
                surveyUpdate.getParticipantCount(),
                surveyUpdate.getHandlingKey());

        return surveyMongoRepository.save(surveyEntity)
                .toModel();
    }

    @Override
    public Survey retrieve(String id) {
        return surveyMongoRepository.findById(id)
                .orElseThrow(null)
                .toModel();
    }

    @Override
    public Page<Survey> paginate(SurveyPaginate surveyPaginate) {
        var pageRequest = PageRequest.of(surveyPaginate.getPage().getPageNumber(), surveyPaginate.getPage().getSize());
        var surveyPage = surveyMongoRepository.findAll(pageRequest);
        var surveys = surveyPage.stream().map(SurveyDocument::toModel).toList();

        return Page.of(surveys, surveyPage.getNumber(), surveyPage.getSize(), surveyPage.getTotalElements());
    }

    @Override
    public Survey retrieveByHandlingKey(String handlingKey) {
        return surveyMongoRepository.findByHandlingKey(handlingKey)
                .orElseThrow(null)
                .toModel();
    }

}
