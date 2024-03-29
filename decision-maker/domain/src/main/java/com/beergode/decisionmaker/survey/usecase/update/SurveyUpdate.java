package com.beergode.decisionmaker.survey.usecase.update;

import com.beergode.decisionmaker.common.model.UseCase;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "surveyUpdate", builderClassName = "Builder")
public class SurveyUpdate implements UseCase {
    private UUID id;
    private String content;
    private QuestionUpdate question;

    private SurveyUpdate(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.question = builder.question;
    }

    public static Builder surveyUpdate() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public SurveyUpdate build() {
            return new SurveyUpdate(this);
        }
    }

    public String getStringId() {
        return this.id.toString();
    }

}
