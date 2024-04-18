package com.beergode.decisionmaker.survey.usecase.update;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "surveySettingCreate", builderClassName = "Builder")
public class SurveySettingUpdate {
    private Integer participantLimit;
    private String passcode;


    private SurveySettingUpdate(SurveySettingUpdate.Builder builder) {
        this.participantLimit = builder.participantLimit;
        this.passcode = builder.passcode;
    }

    public static SurveySettingUpdate.Builder surveySettingUpdate() {
        return new SurveySettingUpdate.Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public SurveySettingUpdate build() {
            return new SurveySettingUpdate(this);
        }
    }
}
