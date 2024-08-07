package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;

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
        var survey = surveyPort.create(useCase);
        return survey.checkCountDown(() -> surveyPort.update(survey.toUpdate()));
    }
}
