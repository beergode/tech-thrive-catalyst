package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class SurveyPaginateUseCaseHandler implements UseCaseHandler<Page<Survey>, SurveyPaginate> {

    private final SurveyPort surveyPort;

    @Override
    public Page<Survey> handle(SurveyPaginate useCase) {
        return surveyPort.paginate(useCase);
    }
}