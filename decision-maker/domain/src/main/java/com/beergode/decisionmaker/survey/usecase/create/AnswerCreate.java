package com.beergode.decisionmaker.survey.usecase.create;

import java.util.Optional;
import java.util.UUID;

import com.beergode.decisionmaker.common.model.UseCase;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Builder(builderMethodName = "answerCreate", builderClassName = "Builder")
public class AnswerCreate implements UseCase {

    private UUID id;
    private String text;
    private Long voteCount;

    private AnswerCreate(Builder builder) {
        this.id = UUID.randomUUID();
        this.text = validateText(builder);
        this.voteCount = 0L;
    }

    private static String validateText(Builder builder) {
        String answerText = builder.text;
        if (answerText == null){
            throw new IllegalArgumentException("Answer text cannot be null");
        }
        return answerText;
    }

    public static Builder answerCreate() {
        return new Builder();
    }

    public static final class Builder {

        private Builder() {
        }

        public AnswerCreate build() {
            return new AnswerCreate(this);
        }
    }

    public String getStringId() {
        return Optional.of(this.id)
                .map(UUID::toString)
                .orElse(null);
    }
}
