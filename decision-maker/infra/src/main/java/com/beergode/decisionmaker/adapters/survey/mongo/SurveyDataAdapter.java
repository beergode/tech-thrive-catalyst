package com.beergode.decisionmaker.adapters.survey.mongo;

import com.beergode.decisionmaker.adapters.survey.mongo.entity.AnswerField;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.QuestionField;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveyDocument;
import com.beergode.decisionmaker.adapters.survey.mongo.entity.SurveySettingEntity;
import com.beergode.decisionmaker.adapters.survey.mongo.repository.SurveyMongoRepository;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.configuration.schedule.SingleTrigger;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurveyDataAdapter implements SurveyPort {
    private final SurveyMongoRepository surveyMongoRepository;
    private final TaskScheduler taskScheduler;

    @Override
    public Survey create(SurveyCreate surveyCreate) {
        var question = surveyCreate.getQuestion();
        var surveySetting = surveyCreate.getSetting();
        var answers = question.getAnswers()
                .stream()
                .map(answerCreate -> AnswerField.of(answerCreate.getStringId(), answerCreate.getText()))
                .toList();
        var questionEntity = QuestionField.of(question.getStringId(), question.getText(), answers);
        var surveySettingEntity = surveySetting == null
                ? null
                : SurveySettingEntity.of(surveySetting.getParticipantLimit());
        var surveyEntity = SurveyDocument.of(surveyCreate.getStringId(),
                surveyCreate.getContent(),
                surveyCreate.getNote(),
                surveyCreate.getCountdownDurationSeconds(),
                questionEntity,
                surveySettingEntity,
                surveyCreate.getStringHandlingKey());

        Survey survey = surveyMongoRepository.save(surveyEntity)
                .toModel();

        scheduleClose(survey);

        return survey;
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
        var questionEntity = QuestionField.of(question.getStringId(), question.getText(), answers);
        var surveySettingEntity = SurveySettingEntity.of(surveySetting == null
                ? null
                : surveySetting.getParticipantLimit());
        var surveyEntity = SurveyDocument.of(surveyUpdate.getStringId(),
                surveyUpdate.getContent(),
                surveyUpdate.getNote(),
                questionEntity,
                surveyUpdate.getClosedAt(),
                surveySettingEntity,
                surveyUpdate.getParticipantCount(),
                surveyUpdate.getHandlingKey());

        return surveyMongoRepository.save(surveyEntity).toModel();
    }

    @Override
    public Survey retrieve(String id) {
        return surveyMongoRepository.findById(id).orElseThrow(null).toModel();
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
        return surveyMongoRepository.findByHandlingKey(handlingKey).orElseThrow(null).toModel();
    }

    private void scheduleClose(Survey survey) {
        Integer countdownDurationSeconds = survey.getCountdownDurationSeconds();
        if (countdownDurationSeconds != null && countdownDurationSeconds != 0) {
            // Schedule the closing of the survey after countdownDurationSeconds
            taskScheduler.schedule(
                    () -> {
                        survey.close();
                        update(survey.toUpdate());
                    },
                    new SingleTrigger(Instant.now().plusSeconds(countdownDurationSeconds))
            );
        }
    }

}
