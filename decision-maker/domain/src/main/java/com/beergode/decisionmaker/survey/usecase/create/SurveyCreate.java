package com.beergode.decisionmaker.survey.usecase.create;

import com.beergode.decisionmaker.common.model.UseCase;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "surveyCreate", builderClassName = "Builder")
public class SurveyCreate implements UseCase {

    private UUID id;
    private String handlingKey;
    private String content;
    private QuestionCreate question;
    private SurveySettingCreate setting;

    private SurveyCreate(Builder builder) {
        this.id = UUID.randomUUID();
        this.handlingKey = UUID.randomUUID().toString();
        this.content = builder.content;
        this.question = builder.question;
        this.setting = builder.setting;
    }

    public static Builder surveyCreate() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public SurveyCreate build() {
            return new SurveyCreate(this);
        }
    }

    public String getStringId(){
        return this.id.toString();
    }

    public String getStringHandlingKey(){
        return this.handlingKey.toString();
    }
}
