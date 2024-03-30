package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyGet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class SurveyRetrieveUseCaseHandler implements UseCaseHandler<Survey, SurveyGet> {

    private final SurveyPort surveyPort;

    @Override
    public Survey handle(SurveyGet surveyGet) {
        Survey survey = surveyPort.retrieve(surveyGet.getId());
        if (!survey.isClosed()) {
            survey.hideVoteCounts();
        }
        return survey;
    }

}