package com.beergode.decisionmaker.survey.usecase.update;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder(builderMethodName = "surveySettingCreate", builderClassName = "Builder")
public class SurveySettingUpdate {
    private Integer participantLimit;
    private boolean isCustomInputEnabled;

    private SurveySettingUpdate(SurveySettingUpdate.Builder builder) {
        this.participantLimit = builder.participantLimit;
        this.isCustomInputEnabled = builder.isCustomInputEnabled;
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
