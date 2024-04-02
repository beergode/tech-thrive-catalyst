package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyGet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
public class SurveyRetrieveUseCaseHandler extends ObservableUseCasePublisher
    implements UseCaseHandler<Survey, SurveyGet> {

  private final SurveyPort surveyPort;

  public SurveyRetrieveUseCaseHandler(SurveyPort surveyPort) {
    this.surveyPort = surveyPort;
    register(SurveyGet.class, this);
  }

  @Override
  public Survey handle(SurveyGet surveyGet) {
    var  survey = surveyPort.retrieveByHandlingKey(surveyGet.getHandlingKey());
    if (!survey.isClosed()) {
      survey.hideVoteCounts();
    }
    return survey;
  }

}