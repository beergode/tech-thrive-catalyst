package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.beergode.decisionmaker.survey.usecase.create.SurveyCreate.surveyCreate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyCreateRequest {
    @NotNull
    private String content;
    @NotNull
    private QuestionCreateRequest question;

    public SurveyCreate toUseCase() {
        return surveyCreate()
                .content(content)
                .question(question.toUseCase())
                .build();
    }
}
