package com.beergode.decisionmaker.survey.usecase.create;

import com.beergode.decisionmaker.survey.model.SurveySetting;
import com.google.common.annotations.VisibleForTesting;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "surveySettingCreate", builderClassName = "Builder")
public class SurveySettingCreate {

    private Integer participantLimit;
    private boolean isCustomInputEnabled;

    private SurveySettingCreate(SurveySettingCreate.Builder builder) {
        this.participantLimit = builder.participantLimit;
        this.isCustomInputEnabled = builder.isCustomInputEnabled;
    }

    public static SurveySettingCreate.Builder surveySettingCreate() {
        return new SurveySettingCreate.Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public SurveySettingCreate build() {
            return new SurveySettingCreate(this);
        }
    }

    @VisibleForTesting
    public SurveySetting toSurveySetting() {
        return SurveySetting.surveySetting()
                .participantLimit(participantLimit)
                .isCustomInputEnabled(isCustomInputEnabled)
                .build();
    }
}
