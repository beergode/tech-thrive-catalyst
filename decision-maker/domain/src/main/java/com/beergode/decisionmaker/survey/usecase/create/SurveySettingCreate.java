package com.beergode.decisionmaker.survey.usecase.create;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "surveySettingCreate", builderClassName = "Builder")
public class SurveySettingCreate {

    private Integer participantLimit;
    private boolean isCustomInputAvailable;

    private SurveySettingCreate(SurveySettingCreate.Builder builder) {
        this.participantLimit = builder.participantLimit;
        this.isCustomInputAvailable = builder.isCustomInputAvailable;
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
}
