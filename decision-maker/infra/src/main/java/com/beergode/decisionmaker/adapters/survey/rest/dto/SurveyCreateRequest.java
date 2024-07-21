package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.create.SurveyCreate;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import java.util.List;
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
    private List<QuestionCreateRequest> questions;
    private SurveySettingRequest setting;

    public SurveyCreate toUseCase() {
        SurveyCreate.Builder builder = surveyCreate()
                .note(note)
                .countdownDurationSeconds(countdownDurationSeconds)
                .content(content)
                .questions(questions.stream()
                        .map(QuestionCreateRequest::toUseCase)
                        .toList());
        if (setting != null) {
            builder.setting(setting.toUseCase());
        }

        return builder.build();
    }
}
