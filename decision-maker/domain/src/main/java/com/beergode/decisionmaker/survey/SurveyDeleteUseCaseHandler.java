package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.VoidUseCaseHandler;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyDelete;
import groovy.util.logging.Slf4j;

@Slf4j
@DomainComponent
public class SurveyDeleteUseCaseHandler extends ObservableUseCasePublisher
        implements VoidUseCaseHandler<SurveyDelete> {

  private final SurveyPort surveyPort;

  public SurveyDeleteUseCaseHandler(SurveyPort surveyPort) {
    this.surveyPort = surveyPort;
    register(SurveyDelete.class, this);
  }

  @Override
  public void handle(SurveyDelete useCase) {
   surveyPort.delete(useCase.getHandlingKey());
  }
}
