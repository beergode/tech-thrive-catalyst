package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import com.beergode.decisionmaker.survey.usecase.create.AnswerCreate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "end", builderClassName = "Builder")
public class FinalizeSurvey implements UseCase {

    private String surveyId;

    private FinalizeSurvey(FinalizeSurvey.Builder builder) {
        this.surveyId = builder.surveyId;
    }

    public static FinalizeSurvey.Builder end() {
        return new FinalizeSurvey.Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public FinalizeSurvey build() {
            return new FinalizeSurvey(this);
        }
    }

}
