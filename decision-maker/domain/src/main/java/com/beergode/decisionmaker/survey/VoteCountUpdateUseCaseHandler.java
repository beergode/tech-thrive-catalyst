package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.VoidUseCaseHandler;
import com.beergode.decisionmaker.survey.model.Answer;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyCreatePort;
import com.beergode.decisionmaker.survey.port.SurveyRetrievePort;
import com.beergode.decisionmaker.survey.port.VoteCountUpdatePort;
import com.beergode.decisionmaker.survey.usecase.VoteCountUpdate;
import groovy.util.logging.Slf4j;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class VoteCountUpdateUseCaseHandler implements VoidUseCaseHandler<VoteCountUpdate> {

  private final VoteCountUpdatePort voteCountUpdatePort;
  private final SurveyRetrievePort surveyRetrievePort;
  private final SurveyCreatePort surveyCreatePort;


  @Override
  public void handle(VoteCountUpdate useCase) {

    var survey = surveyRetrievePort.retrieve(useCase.getId());

    survey.getQuestion()
        .getAnswers()
        .stream()
        .filter(answer -> answer.getId().equals(useCase.getAnswerId()))
        .findFirst()
        .map(answer -> {
          answer.incrementVoteCount();
          return answer;
        });

    //    survey.


    //    voteCountUpdatePort.voteCountUpdate(useCase);
  }


}
