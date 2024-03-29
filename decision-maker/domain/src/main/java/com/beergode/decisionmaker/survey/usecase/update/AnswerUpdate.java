package com.beergode.decisionmaker.survey.usecase.update;

import java.util.Optional;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "answerUpdate", builderClassName = "Builder")
public class AnswerUpdate {

    private UUID id;
    private String text;
    private Long voteCount;

    private AnswerUpdate(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.voteCount = builder.voteCount;
    }

    public static Builder answerUpdate() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public AnswerUpdate build() {
            return new AnswerUpdate(this);
        }
    }

    public String getStringId() {
        return this.id.toString();
    }
}
