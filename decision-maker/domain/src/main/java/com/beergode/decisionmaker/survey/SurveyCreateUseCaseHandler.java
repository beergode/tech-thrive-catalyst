package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyCreatePort;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class SurveyCreateUseCaseHandler implements UseCaseHandler<Survey, SurveyCreate> {

    private final SurveyCreatePort surveyCreatePort;

    @Override
    public Survey handle(SurveyCreate useCase) {
        return surveyCreatePort.create(useCase);
    }
}
