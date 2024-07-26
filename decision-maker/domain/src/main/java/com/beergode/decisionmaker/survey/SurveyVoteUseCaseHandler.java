package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.VoidUseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.vote.SurveyVotes;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
public class SurveyVoteUseCaseHandler extends ObservableUseCasePublisher
    implements VoidUseCaseHandler<SurveyVotes> {

  private final SurveyPort surveyPort;

  public SurveyVoteUseCaseHandler(SurveyPort surveyPort) {
    this.surveyPort = surveyPort;
    register(SurveyVotes.class, this);
  }

  @Override
  public void handle(SurveyVotes useCase) {
    var survey = validate(useCase.surveyId());
    survey.incrementVoteCount(useCase);
    surveyPort.update(survey.toUpdate());
  }

  private Survey validate(String surveyId) {
    var survey = surveyPort.retrieve(surveyId);
    if (survey.isClosed()) {
      log.error("Voting is closed for Survey {}", survey.getId());
      throw new IllegalStateException("Survey is closed");
    }
    return survey;
  }
}
