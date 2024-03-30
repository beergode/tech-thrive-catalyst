package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.create.SurveySettingCreate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.beergode.decisionmaker.survey.usecase.create.SurveySettingCreate.surveySettingCreate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveySettingRequest {

    private Integer participantLimit;

    public SurveySettingCreate toUseCase() {
        return surveySettingCreate()
                .participantLimit(participantLimit)
                .build();
    }
}
