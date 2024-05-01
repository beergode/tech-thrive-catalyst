package com.beergode.decisionmaker.survey.usecase.create;

import com.beergode.decisionmaker.common.model.UseCase;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static java.util.Optional.ofNullable;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "questionCreate", builderClassName = "Builder")
public class QuestionCreate implements UseCase {
    private UUID id;
    private String text;
    private List<AnswerCreate> answers;
    private boolean isMultipleChoice;

    private QuestionCreate(Builder builder) {
        this.id = UUID.randomUUID();
        this.text = builder.text;
        this.answers = validateAnswers(builder);
        this.isMultipleChoice = builder.isMultipleChoice;
    }

    private List<AnswerCreate> validateAnswers(Builder builder) {
        if (builder.isMultipleChoice) {
            var surveyAnswers = builder.answers;
            if (surveyAnswers == null || surveyAnswers.isEmpty()) {
                throw new IllegalArgumentException("Answers cannot be null or empty for a multiple-choice question!");
            }
            return surveyAnswers;
        }
        return null;
    }

    public static Builder questionCreate() {
        return new Builder();
    }

    public static final class Builder {
        private String text;
        private List<AnswerCreate> answers;
        private boolean isMultipleChoice;

        private Builder() {
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder answers(List<AnswerCreate> answers) {
            this.answers = answers;
            return this;
        }

        public Builder isMultipleChoice(boolean isMultipleChoice) {
            this.isMultipleChoice = isMultipleChoice;
            return this;
        }

        public QuestionCreate build() {
            return new QuestionCreate(this);
        }
    }

    public String getStringId() {
        return ofNullable(id)
                .map(UUID::toString)
                .orElse(null);
    }
}
