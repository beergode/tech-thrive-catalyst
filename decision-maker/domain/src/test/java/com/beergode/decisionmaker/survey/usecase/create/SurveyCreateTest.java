package com.beergode.decisionmaker.survey.usecase.create;

import java.util.List;
import org.junit.jupiter.api.Test;

import static com.beergode.decisionmaker.survey.usecase.create.AnswerCreate.answerCreate;
import static com.beergode.decisionmaker.survey.usecase.create.QuestionCreate.questionCreate;
import static com.beergode.decisionmaker.survey.usecase.create.SurveyCreate.surveyCreate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SurveyCreateTest {


    @Test
    void should_allow_creating_surveys() {
        //given
        var surveyCreate = surveyCreate()
                .question(questionCreate()
                        .answers(List.of(answerCreate()
                                        .text("Test text")
                                .build()))
                        .build())
                .build();

        //then
        assertThat(surveyCreate).isNotNull();
    }

    @Test
    void should_not_allow_creating_survey_with_null_answers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> surveyCreate()
                        .question(questionCreate()
                                .answers(null)
                                .build())
                        .build())
                .withMessage("Answers cannot be null! or empty");
    }

    @Test
    void should_not_allow_creating_survey_with_empty_answers() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> surveyCreate()
                        .question(questionCreate()
                                .answers(List.of())
                                .build())
                        .build())
                .withMessage("Answers cannot be null! or empty");
    }

    @Test
    void should_not_allow_creating_survey_answer_without_text() {
        assertThatIllegalArgumentException()
                .isThrownBy( () -> surveyCreate()
                        .question(questionCreate()
                                .answers(List.of(answerCreate()
                                        .text(null)
                                        .build()))
                                .build()))
                .withMessage("Answer text cannot be null");
    }
}