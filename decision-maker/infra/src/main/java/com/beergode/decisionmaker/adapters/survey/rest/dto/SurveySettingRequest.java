package com.beergode.decisionmaker.adapters.survey.rest.dto;

import com.beergode.decisionmaker.survey.usecase.create.SurveySettingCreate;
import com.beergode.decisionmaker.survey.usecase.update.SurveySettingUpdate;
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

    private boolean isCustomInputEnabled;

    public SurveySettingCreate toUseCase() {
        return surveySettingCreate()
                .participantLimit(participantLimit)
                .isCustomInputEnabled(isCustomInputEnabled)
                .build();
    }


    public SurveySettingUpdate toUpdateUseCase() {
        return SurveySettingUpdate.surveySettingUpdate()
                .participantLimit(participantLimit)
                .isCustomInputEnabled(isCustomInputEnabled)
                .build();
    }

}
