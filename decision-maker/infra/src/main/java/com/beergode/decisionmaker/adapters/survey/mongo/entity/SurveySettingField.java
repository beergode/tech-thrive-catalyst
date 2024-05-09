package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.survey.model.SurveySetting;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.beergode.decisionmaker.survey.model.SurveySetting.surveySetting;

@Getter
public class SurveySettingField {

    private Integer participantLimit;
    private boolean isCustomInputAvailable;

    public static SurveySettingField of(Integer participantLimit, boolean isCustomInputAvailable ) {
        return new SurveySettingField(participantLimit, isCustomInputAvailable);
    }

    public SurveySetting toModel() {
        return surveySetting()
                .participantLimit(participantLimit)
                .build();
    }
    private SurveySettingField() { /*Hide No Args Constructor*/}
    private SurveySettingField(Integer participantLimit, boolean isCustomInputAvailable) {
        this.participantLimit = participantLimit;
        this.isCustomInputAvailable = isCustomInputAvailable;
    }
}
