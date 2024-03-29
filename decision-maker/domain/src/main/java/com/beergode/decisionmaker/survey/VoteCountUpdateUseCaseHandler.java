package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.VoidUseCaseHandler;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.VoteCountUpdate;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;

@Slf4j
@DomainComponent
@RequiredArgsConstructor
public class VoteCountUpdateUseCaseHandler implements VoidUseCaseHandler<VoteCountUpdate> {

    private final SurveyPort surveyPort;

    @Override
    public void handle(VoteCountUpdate useCase) {

        var survey = surveyPort.retrieve(useCase.getSurveyId());
        survey.getAnswers()
                .stream()
                .filter(answer -> answer.getStringId().equals(useCase.getAnswerId()))
                .findFirst()
                .map(answer -> {
                    answer.incrementVoteCount();
                    return answer;
                });

        surveyPort.update(survey.toUpdate());
    }
}
