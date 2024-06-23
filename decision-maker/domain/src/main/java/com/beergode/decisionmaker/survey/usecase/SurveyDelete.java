package com.beergode.decisionmaker.survey.usecase;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "end", builderClassName = "Builder")
public class SurveyDelete implements UseCase {

    private String handlingKey;

    private SurveyDelete(SurveyDelete.Builder builder) {
        this.handlingKey = builder.handlingKey;
    }

    public static SurveyDelete.Builder delete() {
        return new SurveyDelete.Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public SurveyDelete build() {
            return new SurveyDelete(this);
        }
    }

}
