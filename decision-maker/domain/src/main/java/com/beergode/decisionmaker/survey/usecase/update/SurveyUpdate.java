package com.beergode.decisionmaker.survey.usecase.update;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "surveyUpdate", builderClassName = "Builder")
public class SurveyUpdate implements UseCase {
    private UUID id;
    private String handlingKey;
    private String content;
    private String note;
    private QuestionUpdate question;
    private LocalDate closedAt;
    private SurveySettingUpdate setting;
    private Integer participantCount;

    private SurveyUpdate(Builder builder) {
        this.id = builder.id;
        this.handlingKey = builder.handlingKey;
        this.content = builder.content;
        this.note = builder.note;
        this.question = builder.question;
        this.closedAt = builder.closedAt;
        this.setting = builder.setting;
        this.participantCount = builder.participantCount;
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
