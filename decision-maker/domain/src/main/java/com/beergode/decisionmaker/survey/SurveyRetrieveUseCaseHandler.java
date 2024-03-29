package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyGet;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class SurveyRetrieveUseCaseHandler implements UseCaseHandler<Survey, SurveyGet> {

    private final SurveyPort surveyPort;

    @Override
    public Survey handle(SurveyGet surveyGet) {
        return surveyPort.retrieve(surveyGet.getId());
    }

}