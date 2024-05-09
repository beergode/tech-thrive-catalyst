package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.model.SurveySetting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveySettingResponse {
    private Integer participantLimit;
    private boolean isCustomInputEnabled;

    public static SurveySettingResponse from(SurveySetting setting) {
        return SurveySettingResponse.builder()
                .participantLimit(setting.getParticipantLimit())
                .isCustomInputEnabled(setting.isCustomInputEnabled())
                .build();
    }
}
