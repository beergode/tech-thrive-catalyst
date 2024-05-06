package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Answer;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.AddAnswer;
import com.beergode.decisionmaker.survey.usecase.SurveyFinalize;
import groovy.util.logging.Slf4j;

@Slf4j
@DomainComponent
public class AddAnswerUseCaseHandler extends ObservableUseCasePublisher
        implements UseCaseHandler<Survey, AddAnswer> {

    private final SurveyPort surveyPort;

    public AddAnswerUseCaseHandler(SurveyPort surveyPort) {
        this.surveyPort = surveyPort;
        register(AddAnswer.class, this);
    }

    @Override
    public Survey handle(AddAnswer useCase) {
        var survey = surveyPort.retrieve(useCase.getSurveyId());
        survey.getQuestion()
                .getAnswers()
                .add(Answer
                        .answer()
                        .text(useCase.getText())
                        .build());
        return surveyPort.update(survey.toUpdate());
    }
}
