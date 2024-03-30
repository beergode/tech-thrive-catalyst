package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "end", builderClassName = "Builder")
public class SurveyFinalize implements UseCase {

    private String surveyId;

    private SurveyFinalize(SurveyFinalize.Builder builder) {
        this.surveyId = builder.surveyId;
    }

    public static SurveyFinalize.Builder end() {
        return new SurveyFinalize.Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public SurveyFinalize build() {
            return new SurveyFinalize(this);
        }
    }

}
