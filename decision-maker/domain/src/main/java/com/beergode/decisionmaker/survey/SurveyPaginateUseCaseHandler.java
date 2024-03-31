package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.model.Page;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyPaginate;
import groovy.util.logging.Slf4j;

@Slf4j
@DomainComponent
public class SurveyPaginateUseCaseHandler extends ObservableUseCasePublisher
    implements UseCaseHandler<Page<Survey>, SurveyPaginate> {

  private final SurveyPort surveyPort;

  public SurveyPaginateUseCaseHandler(SurveyPort surveyPort) {
    this.surveyPort = surveyPort;
    register(SurveyPaginate.class, this);
  }

  @Override
  public Page<Survey> handle(SurveyPaginate useCase) {
    return surveyPort.paginate(useCase);
  }
}