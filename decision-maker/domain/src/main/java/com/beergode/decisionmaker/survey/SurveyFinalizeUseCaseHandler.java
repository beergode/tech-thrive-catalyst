package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyFinalize;
import groovy.util.logging.Slf4j;

@Slf4j
@DomainComponent
public class SurveyFinalizeUseCaseHandler extends ObservableUseCasePublisher
    implements UseCaseHandler<Survey, SurveyFinalize> {

  private final SurveyPort surveyPort;

  public SurveyFinalizeUseCaseHandler(SurveyPort surveyPort) {
    this.surveyPort = surveyPort;
    register(SurveyFinalize.class, this);
  }

  @Override
  public Survey handle(SurveyFinalize useCase) {

    var survey = surveyPort.retrieve(useCase.getSurveyId());
    survey.close();
    return surveyPort.update(survey.toUpdate());
  }
}
