package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class SurveyCreateUseCaseHandler implements UseCaseHandler<Survey, SurveyCreate> {

    private final SurveyPort surveyPort;

    @Override
    public Survey handle(SurveyCreate useCase) {
        return surveyPort.create(useCase);
    }
}
