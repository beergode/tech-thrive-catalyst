package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.survey.model.SurveySetting;
import lombok.Getter;

import static com.beergode.decisionmaker.survey.model.SurveySetting.surveySetting;

@Getter
public class SurveySettingField {

    private Integer participantLimit;
    private boolean isCustomInputEnabled;

    public static SurveySettingField of(Integer participantLimit, boolean isCustomInputEnabled ) {
        return new SurveySettingField(participantLimit, isCustomInputEnabled);
    }

    public SurveySetting toModel() {
        return surveySetting()
                .participantLimit(participantLimit)
                .isCustomInputEnabled(isCustomInputEnabled)
                .build();
    }
    private SurveySettingField() { /*Hide No Args Constructor*/}

    private SurveySettingField(Integer participantLimit, boolean isCustomInputEnabled) {
        this.participantLimit = participantLimit;
        this.isCustomInputEnabled = isCustomInputEnabled;
    }
}
