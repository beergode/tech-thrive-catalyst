package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.survey.model.SurveySetting;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.beergode.decisionmaker.survey.model.SurveySetting.surveySetting;

@Getter
public class SurveySettingField {

    private Integer participantLimit;
    private String passcode;

    public static SurveySettingField of(Integer participantLimit, String passcode) {
        return new SurveySettingField(participantLimit, passcode);
    }

    public SurveySetting toModel() {
        return surveySetting()
                .participantLimit(participantLimit)
                .passcode(passcode)
                .build();
    }

    private SurveySettingField() { /*Hide No Args Constructor*/}

    private SurveySettingField(Integer participantLimit, String passcode) {
        this.participantLimit = participantLimit;
        this.passcode = passcode;
    }
}
