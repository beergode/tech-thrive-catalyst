package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyFinalize;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class SurveyFinalizeUseCaseHandler implements UseCaseHandler<Survey, SurveyFinalize> {

    private final SurveyPort surveyPort;

    @Override
    public Survey handle(SurveyFinalize useCase) {

        var survey = surveyPort.retrieve(useCase.getSurveyId());
        survey.close();
        return surveyPort.update(survey.toUpdate());
    }
}
