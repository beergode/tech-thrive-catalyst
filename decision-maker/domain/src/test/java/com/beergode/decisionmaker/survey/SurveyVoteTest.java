package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.beergode.decisionmaker.survey.usecase.create.AnswerCreate.answerCreate;
import static com.beergode.decisionmaker.survey.usecase.create.QuestionCreate.questionCreate;
import static com.beergode.decisionmaker.survey.usecase.create.SurveyCreate.surveyCreate;

class SurveyVoteTest {

    @Test
    void should_allow_vote() {
        //given
        var surveyCreate =createDemoSurvey();
        SurveyFakeAdapter surveyPort = new SurveyFakeAdapter(currentBalance, currentBalance);
    }

    @Test
    void should_not_allow_vote_when_survey_closed() {
    }

    private static SurveyCreate createDemoSurvey() {
        return surveyCreate()
                .content("Soccer match")
                .question(questionCreate()
                        .text("Will Real Madrid become champion this year?")
                        .answers(List.of(answerCreate()
                                        .text("Yes")
                                        .build(),
                                answerCreate()
                                        .text("No")
                                        .build()))
                        .build())
                .build();
    }
}