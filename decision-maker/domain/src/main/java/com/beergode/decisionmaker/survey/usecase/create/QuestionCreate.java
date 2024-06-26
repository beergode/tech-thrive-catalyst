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

    private QuestionCreate(Builder builder) {
        this.id = UUID.randomUUID();
        this.text = builder.text;
        this.answers = builder.answers;
    }

    public static Builder questionCreate() {
        return new Builder();
    }

    public static final class Builder {
        private String text;
        private List<AnswerCreate> answers;

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

        public QuestionCreate build() {
            return new QuestionCreate(this);
        }
    }

    public String getStringId() {
        return ofNullable(id).map(UUID::toString).orElse(null);
    }
}
