package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.VoidUseCaseHandler;
import com.beergode.decisionmaker.survey.model.Answer;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.AddAnswer;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@DomainComponent
public class AddAnswerUseCaseHandler extends ObservableUseCasePublisher
        implements VoidUseCaseHandler<AddAnswer> {

    private final SurveyPort surveyPort;

    public AddAnswerUseCaseHandler(SurveyPort surveyPort) {
        this.surveyPort = surveyPort;
        register(AddAnswer.class, this);
    }

    @Override
    public void handle(AddAnswer useCase) {
        var survey = surveyPort.retrieve(useCase.getSurveyId());
        if (survey.isClosed()) {
            log.error("Adding answer is closed for Survey {}", survey.getId());
            throw new IllegalStateException("Survey is closed");
        }
        boolean customInputEnabled = survey.getSetting().isCustomInputEnabled();
        if (!customInputEnabled) {
            log.error("Custom input is not enabled for Survey {}", survey.getId());
            throw new IllegalStateException("Custom input is not enabled!");
        }
        survey.getQuestion()
                .getAnswers()
                .add(Answer
                        .answer()
                        .text(useCase.getText())
                        .build());
        surveyPort.update(survey.toUpdate());
    }
}
