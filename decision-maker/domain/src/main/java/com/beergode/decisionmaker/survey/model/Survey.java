package com.beergode.decisionmaker.survey.model;

import com.beergode.decisionmaker.survey.usecase.update.SurveyUpdate;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "survey", builderClassName = "Builder")
public class Survey {

    private UUID id;
    private String content;
    private Instant createdAt;
    private LocalDate closedAt;

    private Question question;

    private Survey(Builder builder) {
        this.id = builder.id;
        this.content = builder.content;
        this.question = builder.question;
        this.closedAt = builder.closedAt;
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
                .closedAt(this.closedAt)
                .build();
    }

    public Long getCreatedLong() {
        return createdAt == null ? null : createdAt.toEpochMilli();
    }

    public void close() {
       this.closedAt = LocalDate.now();
    }

    public boolean isClosed() {
       return this.closedAt != null;
    }

}
