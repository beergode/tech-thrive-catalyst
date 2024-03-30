package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.VoidUseCaseHandler;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.SurveyVote;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class SurveyVoteUseCaseHandler implements VoidUseCaseHandler<SurveyVote> {

    private final SurveyPort surveyPort;

    @Override
    public void handle(SurveyVote useCase) {

        var survey = surveyPort.retrieve(useCase.getSurveyId());
        if(survey.isClosed()) {
            log.error("Voting is closed for Survey {}", survey.getId());
            throw new IllegalStateException("Survey is closed");
        }

        survey.incrementVoteCount(useCase);

        surveyPort.update(survey.toUpdate());
    }
}
