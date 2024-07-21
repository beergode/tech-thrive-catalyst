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
import com.beergode.decisionmaker.survey.usecase.create.QuestionCreate;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.update.QuestionUpdate;
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
        var questionFields = surveyCreate.getQuestions()
                .stream()
                .map(this::extractQuestion)
                .toList();
        var surveySetting = surveyCreate.getSetting();
        var surveySettingField = ofNullable(surveySetting)
                .map(setting -> SurveySettingField.of(setting.getParticipantLimit(), setting.isCustomInputEnabled()))
                .orElse(null);
        var surveyDocument = SurveyDocument.of(surveyCreate.getStringId(),
                surveyCreate.getContent(),
                surveyCreate.getNote(),
                surveyCreate.getCountdownDurationSeconds(),
                questionFields,
                surveySettingField,
                surveyCreate.getHandlingKey());

        return surveyMongoRepository.save(surveyDocument)
                .toModel();
    }

    @Override
    public Survey update(SurveyUpdate surveyUpdate) {
        var questionFields = surveyUpdate.getQuestions()
                .stream()
                .map(this::extractQuestion)
                .toList();
        var surveySetting = surveyUpdate.getSetting();
        var surveySettingField = ofNullable(surveySetting)
                .map(setting -> SurveySettingField.of(setting.getParticipantLimit(), setting.isCustomInputEnabled()))
                .orElse(null);
        var surveyEntity = SurveyDocument.of(surveyUpdate.getStringId(),
                surveyUpdate.getContent(),
                surveyUpdate.getNote(),
                surveyUpdate.getCountdownDurationSeconds(),
                questionFields,
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

    @Override
    public void delete(String handlingKey) {
        surveyMongoRepository.deleteByHandlingKey(handlingKey);
    }

    private QuestionField extractQuestion(QuestionCreate questionCreate) {
        List<AnswerField> answers = questionCreate.getAnswers().isEmpty()
                ? List.of()
                : questionCreate.getAnswers()
                        .stream()
                        .map(answerCreate -> AnswerField.of(answerCreate.getStringId(), answerCreate.getText()))
                        .toList();
        return QuestionField.of(questionCreate.getStringId(), questionCreate.getText(), answers);
    }


    private QuestionField extractQuestion(QuestionUpdate questionUpdate) {
        var answers = questionUpdate.getAnswers()
                .stream()
                .map(answerUpdate ->
                        AnswerField.of(answerUpdate.getStringId(), answerUpdate.getText(),
                                answerUpdate.getVoteCount()))
                .toList();
        return QuestionField.of(questionUpdate.getStringId(), questionUpdate.getText(), answers);
    }
}
