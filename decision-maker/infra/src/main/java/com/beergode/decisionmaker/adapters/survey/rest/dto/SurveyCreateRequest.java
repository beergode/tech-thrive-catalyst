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
    private String note;
    private Integer countdownDurationSeconds;
    @NotNull
    private QuestionCreateRequest question;
    private SurveySettingRequest setting;

    public SurveyCreate toUseCase() {
        SurveyCreate.Builder builder = surveyCreate()
                .note(note)
                .countdownDurationSeconds(countdownDurationSeconds)
                .content(content)
                .question(question.toUseCase());
        if (setting != null) {
            builder.setting(setting.toUseCase());
        }

        return builder.build();
    }
}
