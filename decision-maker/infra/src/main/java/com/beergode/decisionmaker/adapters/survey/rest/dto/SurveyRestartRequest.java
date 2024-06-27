package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRestartRequest {

    private UUID id;
    @NotNull
    private String content;
    private String note;
    private String handlingKey;
    private Integer countdownDurationSeconds;
    @NotNull
    private QuestionUpdateRequest question;
    private SurveySettingRequest setting;

    public SurveyUpdate toUseCase() {
        SurveyUpdate.Builder builder = SurveyUpdate.surveyUpdate()
                .id(id)
                .note(note)
                .handlingKey(handlingKey)
                .countdownDurationSeconds(countdownDurationSeconds)
                .content(content)
                .question(question.toUseCase());
        if (setting != null) {
            builder.setting(setting.toUpdateUseCase());
        }

        return builder.build();
    }
}
