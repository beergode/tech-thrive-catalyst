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

    //    @Test
    //    void should_not_allow_creating_survey_with_null_answers_for_multiple_choice_question() {
    //        assertThatIllegalArgumentException()
    //                .isThrownBy(() -> surveyCreate()
    //                        .question(questionCreate()
    //                                .answers(null)
    //                                .build())
    //                        .build())
    //                .withMessage("Answers cannot be null or empty for a multiple-choice question!");
    //    }
    //
    //    @Test
    //    void should_not_allow_creating_survey_with_empty_answers_for_multiple_choice_question() {
    //        assertThatIllegalArgumentException()
    //                .isThrownBy(() -> surveyCreate()
    //                        .question(questionCreate()
    //                                .answers(List.of())
    //                                .build())
    //                        .build())
    //                .withMessage("Answers cannot be null or empty for a multiple-choice question!");
    //    }

    //    @Test
    //    void should_not_allow_creating_survey_answer_without_text() {
    //        assertThatIllegalArgumentException()
    //                .isThrownBy(() -> surveyCreate()
    //                        .question(questionCreate()
    //                                .answers(List.of(answerCreate()
    //                                        .text(null)
    //                                        .build()))
    //                                .build()))
    //                .withMessage("Answer text cannot be null");
    //    }

    @Test
    void should_allow_adding_note_when_creating_survey() {
        //given
        var surveyCreate = surveyCreate()
                .note("Test note")
                .question(questionCreate()
                        .answers(List.of(answerCreate()
                                .text("Test text")
                                .build()))
                        .build())
                .build();

        //then
        assertThat(surveyCreate).isNotNull();
    }
}