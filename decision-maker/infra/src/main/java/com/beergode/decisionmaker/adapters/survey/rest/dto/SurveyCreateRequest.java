package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.model.Question;
import com.beergode.decisionmaker.survey.usecase.SurveyCreate;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        return SurveyCreate.builder()
                .content(content)
                .question(question.toUseCase())
                .build();
    }
}
