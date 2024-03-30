package com.beergode.decisionmaker.survey;

import com.beergode.decisionmaker.survey.adapters.SurveyFakeAdapter;
import com.beergode.decisionmaker.survey.model.Survey;
import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.beergode.decisionmaker.survey.model.Answer.answer;
import static com.beergode.decisionmaker.survey.model.Question.question;
import static com.beergode.decisionmaker.survey.model.Survey.survey;
import static com.beergode.decisionmaker.survey.usecase.create.AnswerCreate.answerCreate;
import static com.beergode.decisionmaker.survey.usecase.create.QuestionCreate.questionCreate;
import static com.beergode.decisionmaker.survey.usecase.create.SurveyCreate.surveyCreate;

class SurveyVoteTest {

    @Test
    void should_allow_vote() {
        //given
        var surveyCreate = createDemoSurveyCreate();
        var survey = createDemoSurvey();
        SurveyFakeAdapter surveyPort = new SurveyFakeAdapter(survey);
    }

    @Test
    void should_not_allow_vote_when_survey_closed() {
    }

    private static SurveyCreate createDemoSurveyCreate() {
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

    private Survey createDemoSurvey() {
        return survey()
                .content("Soccer match")
                .question(question()
                        .text("Will Real Madrid become champion this year?")
                        .answers(List.of(answer()
                                        .text("Yes")
                                        .build(),
                                answer()
                                        .text("No")
                                        .build()))
                        .build())
                .build();
    }
}