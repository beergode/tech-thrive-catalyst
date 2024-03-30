package com.beergode.decisionmaker.adapters.survey.mongo.entity;

import com.beergode.decisionmaker.survey.model.SurveySetting;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.beergode.decisionmaker.survey.model.SurveySetting.surveySetting;

@Getter
@Document(collection = "surveySetting")
public class SurveySettingEntity {

    private Integer participantLimit;
    public static SurveySettingEntity of(Integer participantLimit) {
        return new SurveySettingEntity(participantLimit);
    }

    public SurveySetting toModel() {
        return surveySetting()
                .participantLimit(participantLimit)
                .build();
    }
    private SurveySettingEntity() { /*Hide No Args Constructor*/}
    private SurveySettingEntity(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }
}
