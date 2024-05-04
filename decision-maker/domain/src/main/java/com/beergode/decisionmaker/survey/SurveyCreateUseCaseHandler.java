package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import groovy.util.logging.Slf4j;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@DomainComponent
public class SurveyCreateUseCaseHandler extends ObservableUseCasePublisher
        implements UseCaseHandler<Survey, SurveyCreate> {
    private final SurveyPort surveyPort;

    public SurveyCreateUseCaseHandler(SurveyPort surveyPort) {
        this.surveyPort = surveyPort;
        register(SurveyCreate.class, this);
    }

    @Override
    public Survey handle(SurveyCreate useCase) {
        Survey survey = surveyPort.create(useCase);
        var countdownDurationSeconds = survey.getCountdownDurationSeconds();
        if (countdownDurationSeconds != null && countdownDurationSeconds != 0) {
            scheduleClose(survey);
        }
        return survey;
    }

    private void scheduleClose(Survey survey) {
        var timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                survey.close();
                surveyPort.update(survey.toUpdate());
            }
        }, survey.getCountdownDurationSeconds() * 1000L);
    }
}
