package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.survey.model.SurveySetting;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.beergode.decisionmaker.survey.model.SurveySetting.surveySetting;

@Getter
public class SurveySettingField {

    private Integer participantLimit;
    public static SurveySettingField of(Integer participantLimit) {
        return new SurveySettingField(participantLimit);
    }

    public SurveySetting toModel() {
        return surveySetting()
                .participantLimit(participantLimit)
                .build();
    }
    private SurveySettingField() { /*Hide No Args Constructor*/}
    private SurveySettingField(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }
}
