package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.common.DomainComponent;
import com.beergode.decisionmaker.common.usecase.ObservableUseCasePublisher;
import com.beergode.decisionmaker.common.usecase.UseCaseHandler;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.port.SurveyPort;
import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;

@DomainComponent
public class SurveyUpdateUseCaseHandler extends ObservableUseCasePublisher
        implements UseCaseHandler<Survey, SurveyUpdate> {
    private final SurveyPort surveyPort;

    public SurveyUpdateUseCaseHandler(SurveyPort surveyPort) {
        this.surveyPort = surveyPort;
        register(SurveyUpdate.class, this);
    }

    @Override
    public Survey handle(SurveyUpdate useCase) {

        useCase.getQuestions().forEach(question ->
                question.getAnswers().forEach(answer -> {
                    answer.setVoteCount(0L);
                    answer.setCustom(false);
                })
        );

        if (useCase.getSetting() != null) {
            useCase.getSetting().setCustomInputEnabled(false);
        }

        var survey = surveyPort.update(useCase);
        return survey.checkCountDown(() -> surveyPort.update(survey.toUpdate()));
    }
}
