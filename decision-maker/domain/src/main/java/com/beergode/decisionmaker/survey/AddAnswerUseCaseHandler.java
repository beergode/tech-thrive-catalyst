package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.VoidUseCaseHandler;
import com.beergode.decisionmaker.survey.model.Answer;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.answer.AddAnswer;
import com.beergode.decisionmaker.survey.usecase.answer.AddAnswers;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@DomainComponent
public class AddAnswerUseCaseHandler extends ObservableUseCasePublisher
        implements VoidUseCaseHandler<AddAnswers> {

    private final SurveyPort surveyPort;

    public AddAnswerUseCaseHandler(SurveyPort surveyPort) {
        this.surveyPort = surveyPort;
        register(AddAnswers.class, this);
    }

    @Override
    public void handle(AddAnswers useCase) {
        var survey = surveyPort.retrieve(useCase.surveyId());
        if (survey.isClosed()) {
            log.error("Adding answer is closed for Survey {}", survey.getId());
            throw new IllegalStateException("Survey is closed");
        }
        boolean customInputEnabled = survey.getSetting().isCustomInputEnabled();
        if (!customInputEnabled) {
            log.error("Custom input is not enabled for Survey {}", survey.getId());
            throw new IllegalStateException("Custom input is not enabled!");
        }
        useCase.addAnswers().forEach(addAnswer -> {
            var answer = Answer.answer()
                    .text(addAnswer.text())
                    .build();
            survey.addAnswer(answer, addAnswer.questionId());
        });
        surveyPort.update(survey.toUpdate());
    }
}
