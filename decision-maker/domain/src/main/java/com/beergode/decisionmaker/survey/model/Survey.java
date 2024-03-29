package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "survey", builderClassName = "Builder")
public class Survey {

    private UUID id;
    private String content;
    private Instant createdAt;
    private OffsetDateTime closedAt;

    private Question question;

    private Survey(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.question = builder.question;
    }

    public static Builder survey() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public Survey build() {
            return new Survey(this);
        }
    }

    public List<Answer> getAnswers() {
        if (question == null) {
            return List.of();
        }
        return question.getAnswers();
    }

    public SurveyUpdate toUpdate(){
        return SurveyUpdate.surveyUpdate()
                .id(this.id)
                .content(this.content)
                .question(this.question.toUpdate())
                .build();
    }

    public Long getCreatedLong() {
        return createdAt == null ? null : createdAt.toEpochMilli();
    }


}
