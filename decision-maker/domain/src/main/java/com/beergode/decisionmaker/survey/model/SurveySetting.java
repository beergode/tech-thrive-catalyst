package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.update.SurveySettingUpdate;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.beergode.decisionmaker.survey.usecase.update.SurveySettingUpdate.surveySettingUpdate;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "surveySetting", builderClassName = "Builder")
public class SurveySetting {

    private Integer participantLimit;
    private boolean isCustomInputEnabled;

    public SurveySettingUpdate toUpdate() {
        return surveySettingUpdate()
                .participantLimit(this.participantLimit)
                .isCustomInputEnabled(this.isCustomInputEnabled)
                .build();
    }
    private SurveySetting(SurveySetting.Builder builder) {
        this.participantLimit = builder.participantLimit;
        this.isCustomInputEnabled = builder.isCustomInputEnabled;
    }

    public static SurveySetting.Builder surveySetting() {
        return new SurveySetting.Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public SurveySetting build() {
            return new SurveySetting(this);
        }
    }
}
