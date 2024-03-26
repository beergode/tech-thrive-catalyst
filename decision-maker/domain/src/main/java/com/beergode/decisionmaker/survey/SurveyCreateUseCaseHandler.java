package com.beergode.decisionmaker.survey;


import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
public class SurveyCreateUseCaseHandler extends ObservableUseCasePublisher
    implements UseCaseHandler<Survey, SurveyCreate> {


  private final SurveyPort surveyPort;

  public SurveyCreateUseCaseHandler(SurveyPort surveyPort) {
    this.surveyPort = surveyPort;
    register(SurveyCreate.class, this);
  }

  @Override
  //  @Transactional
  public Survey handle(SurveyCreate useCase) {
    Survey survey = null;
    try {
      survey = surveyPort.create(useCase);
    } catch (Exception e) {
      log.info("Error message {}", e.getMessage());
      // TODO: Exception to be added
    }
    return survey;
  }
}